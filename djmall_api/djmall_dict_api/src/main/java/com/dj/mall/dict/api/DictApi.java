package com.dj.mall.dict.api;

import com.dj.mall.common.base.BusinessException;
import com.dj.mall.dict.dto.DictDTO;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 14:48
 */
public interface DictApi {

    /**
     * 展示dict 通过code查
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
    DictDTO findByUpdCode(String code)throws Exception;

    /**
     *
     * @param dictDTO
     */
    void updateDict(DictDTO dictDTO) throws BusinessException;
}
