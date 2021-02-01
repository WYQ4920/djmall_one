package com.dj.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.product.bo.ProductBO;
import com.dj.mall.product.entity.ProductEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/2/1 11:25
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {

    /**
     * 获取商品分类
     * @return
     * @throws DataAccessException
     */
    List<ProductBO> findProductAndDict() throws DataAccessException;
}
