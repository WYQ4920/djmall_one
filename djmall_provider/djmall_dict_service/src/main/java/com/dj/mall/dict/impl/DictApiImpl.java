package com.dj.mall.dict.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.api.attr.ProductAttrValueApi;
import com.dj.mall.dict.api.sku.SkuApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;
import com.dj.mall.dict.entity.DictEntity;
import com.dj.mall.dict.mapper.dict.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 15:02
 */
@Service
public class DictApiImpl extends ServiceImpl<DictMapper, DictEntity> implements DictApi {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductAttrValueApi productAttrValueApi;

    /**
     * 通过code查
     *
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public List<DictDTO> findByCode(String code) throws Exception {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code).or().eq("parent_code", code);
        List<DictEntity> list = super.list(queryWrapper);
        return DozerUtil.mapList(list, DictDTO.class);
    }

    /**
     * 查重
     *
     * @param dictName
     * @return
     * @throws Exception
     */
    @Override
    public Boolean findBydictName(String dictName) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dict_name", dictName);
        DictEntity dictEntity = super.getOne(queryWrapper);
        return dictEntity == null? true:false;
    }

    /**
     * add dict 新增字典
     *
     * @param dictDTO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDict(DictDTO dictDTO) throws Exception {
        DictEntity dictEntity = DozerUtil.map(dictDTO, DictEntity.class);
        Boolean aBoolean = this.findBydictName(dictEntity.getDictName());
        if (aBoolean) {
            super.save(dictEntity);
            // 新增数据存入缓存
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.put(dictDTO.getParentCode(),dictDTO.getCode(),dictDTO.getDictName());
            return;
        }
        throw new BusinessException("重名");
    }

    /**
     * to upd
     *
     * @param code
     * @return
     */
    @Override
    public DictDTO findByUpdCode(String code) {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code);
        DictEntity dictEntity = super.getOne(queryWrapper);
        return DozerUtil.map(dictEntity, DictDTO.class);
    }

    /**
     * 修改字典
     * @param dictDTO
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(DictDTO dictDTO) throws BusinessException {
        DictEntity dictEntity = DozerUtil.map(dictDTO, DictEntity.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dict_name", dictEntity.getDictName());
        DictEntity dictEntity1 = super.getOne(queryWrapper);
        if(null != dictEntity1 && !dictEntity.getCode().equals(dictEntity.getCode())){
            throw new BusinessException("重名");
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        DictEntity dictEntity2 = new DictEntity();
        dictEntity2.setDictName(dictEntity.getDictName());
        queryWrapper1.eq("code", dictEntity.getCode());
        super.update(dictEntity2, queryWrapper1);
        // 修改数据存入缓存
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(dictDTO.getParentCode(),dictDTO.getCode(),dictDTO.getDictName());
    }

    /**
     * 根据父级code获取字典信息
     * @param dictCode
     * @return
     * @throws Exception
     */
    @Override
    public List<DictDTO> findDictByCode(String dictCode) throws Exception {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_code", dictCode);
        return DozerUtil.mapList(super.list(queryWrapper), DictDTO.class);
    }

    /**
     * 展示全部字典数据
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<DictDTO> findAllDict() throws Exception {

        return DozerUtil.mapList(super.list(),DictDTO.class);
    }

    /**
     * 根据sku商品类型查询商品属性
     * @param productType 商品类型
     * @return
     */
    @Override
    public List<ProductAttrDTO> findAttrAndSku(String productType) throws Exception{
        // 根据商品类型获得商品属性名
        List<ProductAttrDTO> attrList = DozerUtil.mapList(getBaseMapper().findAttrByProductType(productType), ProductAttrDTO.class);
        // 查询商品属性值全部
        List<ProductAttrValueDTO> productAttrValue = productAttrValueApi.findProductAttrValue();
        // 根据商品名id获得商品属性值
        for (ProductAttrDTO attr : attrList) {
            // 把属性值以逗号拆分
            String[] attrValues = attr.getAttrValue().split(",");
            // 商品属性值集合
            List<ProductAttrValueDTO> attrValueList = new ArrayList<>();
            for (String value : attrValues) {
                // 商品属性值实体类
                ProductAttrValueDTO attrValue = new ProductAttrValueDTO();
                for (ProductAttrValueDTO i : productAttrValue) {
                    if (value.equals(i.getAttrValue())) {
                        attrValue.setId(i.getId());
                        attrValue.setAttrValue(value);
                    }
                }
                attrValueList.add(attrValue);
            }
            attr.setAttrValueList(attrValueList);
        }
        return attrList;
    }

}
