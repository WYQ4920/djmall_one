package com.dj.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.mall.product.bo.ProductBO;
import com.dj.mall.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 商品展示
     * @param productBOPage
     * @param productBO
     * @return
     * @throws DataAccessException
     */
    IPage<ProductBO> findProductAll(Page<ProductBO> productBOPage, @Param("product") ProductBO productBO) throws DataAccessException;
}
