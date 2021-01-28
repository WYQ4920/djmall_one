package com.dj.mall.dict.impl.attr;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.attr.ProductAttrValueApi;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;
import com.dj.mall.dict.entity.attr.ProductAttrEntity;
import com.dj.mall.dict.entity.attr.ProductAttrValueEntity;
import com.dj.mall.dict.mapper.attr.ProductAttrValueMapper;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/26 19:56
 */

@Service
public class ProductAttrValueApiImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttrValueApi {

    /**
     * 展示商品属性值表
     * @param attrId
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductAttrValueDTO> findProductAttrValueAll(Integer attrId) throws Exception {
        QueryWrapper<ProductAttrValueEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", attrId);
        return DozerUtil.mapList(super.list(queryWrapper), ProductAttrValueDTO.class);
    }

    /**
     * 新增属性值
     * @param productAttrValueDTO
     * @throws BusinessException
     */
    @Override
    public void addAttrValue(ProductAttrValueDTO productAttrValueDTO) throws BusinessException {
        QueryWrapper<ProductAttrValueEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_value", productAttrValueDTO.getAttrValue());
        ProductAttrValueEntity one = super.getOne(queryWrapper);
        if (null != one && one.getAttrId().equals(productAttrValueDTO.getAttrId())) {
            throw new BusinessException("已有该属性值");
        }
        super.save(DozerUtil.map(productAttrValueDTO, ProductAttrValueEntity.class));
    }

    /**
     * 移除属性值
     * @param id
     * @throws BusinessException
     */
    @Override
    public void removeAttrValue(Integer id) throws BusinessException {
        QueryWrapper<ProductAttrValueEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        super.remove(queryWrapper);
    }

    /**
     * 根据属性值查询
     * @param
     * @return
     * @throws BusinessException
     */
    @Override
    public List<ProductAttrValueDTO> findProductAttrValue() throws BusinessException {
        return DozerUtil.mapList(super.list(), ProductAttrValueDTO.class);
    }
}
