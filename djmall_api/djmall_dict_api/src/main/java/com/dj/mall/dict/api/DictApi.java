package com.dj.mall.dict.api;

import com.dj.mall.common.base.BusinessException;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 14:48
 */
public interface DictApi {

    /**
     * 通过code查
     * @param code
     * @return
     * @throws Exception
     */
    List<DictDTO> findByCode(String code) throws Exception;

    /**
     * 查重名
     * @param dictName
     * @return
     * @throws Exception
     */
    Boolean findBydictName(String dictName) throws Exception;

    /**
     * add dict
     * @param dictDTO
     * @throws Exception
     */
    void addDict(DictDTO dictDTO) throws Exception;

    /**
     * to upd
     * @param code
     * @return
     */
    DictDTO findByUpdCode(String code) throws Exception;

    /**
     *
     * @param dictDTO
     */
    void updateDict(DictDTO dictDTO) throws BusinessException;

    /**
     * 根据父级code获取字典信息
     * @param dictCode
     * @return
     * @throws Exception
     */
    List<DictDTO> findDictByCode(String dictCode)throws Exception;

    /**
     * 展示全部字典数据
     * @return
     * @throws Exception
     */
    List<DictDTO> findAllDict() throws Exception;

    /**
     * 根据sku商品类型查询商品属性
     * @param productType 商品类型
     * @return
     */
    List<ProductAttrDTO> findAttrAndSku(String productType) throws Exception;

    /**
     * 根据父级code查询
     * @param parentCode
     * @return
     * @throws Exception
     */
    List<DictDTO> findByParentCode(String parentCode) throws Exception;
}
