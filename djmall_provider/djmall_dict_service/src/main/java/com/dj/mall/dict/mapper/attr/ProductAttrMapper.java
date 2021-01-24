package com.dj.mall.dict.mapper.attr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.attr.ProductAttrBO;
import com.dj.mall.dict.dto.attr.ProductAttrValueDTO;
import com.dj.mall.dict.entity.attr.ProductAttrEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/24 12:18
 */
public interface ProductAttrMapper extends BaseMapper<ProductAttrEntity> {

    /**
     * 展示商品属性表
     * @return
     * @throws DataAccessException
     */
    List<ProductAttrBO> findProductAttrAll() throws DataAccessException;

    List<ProductAttrValueDTO> findProductAttrValueAll(Integer id);
}
