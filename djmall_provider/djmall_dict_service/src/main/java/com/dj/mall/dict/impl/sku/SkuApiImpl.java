package com.dj.mall.dict.impl.sku;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.sku.SkuApi;
import com.dj.mall.dict.bo.sku.SkuBO;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;
import com.dj.mall.dict.entity.Sku.SkuEntity;
import com.dj.mall.dict.mapper.sku.SkuMapper;
import lombok.Data;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:11
 */
@Service
public class SkuApiImpl extends ServiceImpl<SkuMapper, SkuEntity> implements SkuApi {
    /**
     * sku展示
     *
     * @param productType
     * @return
     */
    @Override
    public List<SkuDTO> findByProductType(String productType) {
        List<SkuBO> list = this.baseMapper.findByProductType(productType);
        for (SkuBO skuBO : list) {
            if (null == skuBO.getAttrName()){
                skuBO.setDictName("null");
            }
        }
        return DozerUtil.mapList(list, SkuDTO.class);
    }

    /**
     * code 查
     *
     * @param code
     * @return
     */
    @Override
    public List<SkuDTO> findByCode(String code) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_type", code);
        return super.list(queryWrapper);
    }

    /**
     * 关联
     *
     * @param skuDTO
     */
    @Override
    public void updateSku(SkuDTO skuDTO) {
        SkuBO skuBO = DozerUtil.map(skuDTO, SkuBO.class);

        //将AttrIds转为Integer数组
        Integer[] attrIdArr = (Integer[]) ConvertUtils.convert(skuBO.getAttrIds().split(","),Integer.class);

        //创建新增的实体类集合 放入 attrId productType
        SkuEntity skuEntity  = new SkuEntity();
        List<SkuEntity> addList = new ArrayList<>();
        for (Integer i : attrIdArr) {
            skuEntity.setAttrId(i);
            skuEntity.setProductType(skuBO.getProductType());
        }

        //删除old
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_type", skuBO.getProductType());
        super.remove(queryWrapper);

        // 新增new
        super.saveBatch(addList);


    }
}
