package com.dj.mall.dict.api.freight;

import com.dj.mall.common.base.BusinessException;
import com.dj.mall.dict.dto.freight.FreightDTO;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:35
 */
public interface FreightApi {

    /**
     * 展示运费
     * @return
     * @throws Exception
     */
    List<FreightDTO> findFreightAll() throws Exception;

    /**
     * 新增运费
     * @param freightDTO 运费信息
     * @throws Exception
     */
    void addFreight(FreightDTO freightDTO) throws BusinessException;

    /**
     * 获取修改的运费信息
     * @param id 修改id
     * @return
     * @throws Exception
     */
    FreightDTO findFreightById(Integer id) throws Exception;

    /**
     * 修改运费
     * @param freightDTO
     * @throws Exception
     */
    void updateFreight(FreightDTO freightDTO) throws BusinessException;
}
