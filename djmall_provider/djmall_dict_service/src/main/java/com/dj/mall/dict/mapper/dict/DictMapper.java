package com.dj.mall.dict.mapper.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.attr.ProductAttrBO;
import com.dj.mall.dict.bo.attr.ProductAttrValueBO;
import com.dj.mall.dict.bo.sku.SkuBO;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.entity.DictEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 15:15
 */
public interface DictMapper extends BaseMapper<DictEntity> {

    /**
     * 根据sku商品类型查询商品属性名
     * @param productType 商品类型
     * @return
     * @throws DataAccessException
     */
    List<ProductAttrBO> findAttrByProductType(String productType) throws DataAccessException;
}
