package com.dj.mall.dict.impl.attr;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.bo.attr.ProductAttrBO;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.entity.attr.ProductAttrEntity;
import com.dj.mall.dict.mapper.attr.ProductAttrMapper;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:17
 */

@Service
public class ProductAttrApiImpl extends ServiceImpl<ProductAttrMapper, ProductAttrEntity> implements ProductAttrApi {

    /**
     * 展示商品属性表
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductAttrDTO> findProductAttrAll() throws Exception {
        List<ProductAttrBO> productAttrBOList = getBaseMapper().findProductAttrAll();
        return DozerUtil.mapList(productAttrBOList, ProductAttrDTO.class);
    }

    /**
     * 新增属性名
     * @param productAttrDTO
     * @throws BusinessException
     */
    @Override
    public void addAttr(ProductAttrDTO productAttrDTO) throws BusinessException {
        QueryWrapper<ProductAttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_name", productAttrDTO.getAttrName());
        ProductAttrEntity one = super.getOne(queryWrapper);
        if (null != one) {
            throw new BusinessException("已有该属性名");
        }
        super.save(DozerUtil.map(productAttrDTO, ProductAttrEntity.class));
    }

    /**
     * 根据商品属性id查
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ProductAttrDTO findAttrById(Integer id) throws Exception {
        return DozerUtil.map(super.getById(id), ProductAttrDTO.class);
    }

}
