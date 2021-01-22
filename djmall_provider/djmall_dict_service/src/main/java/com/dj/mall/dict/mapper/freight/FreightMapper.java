package com.dj.mall.dict.mapper.freight;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.dict.bo.freight.FreightBO;
import com.dj.mall.dict.entity.freight.FreightEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:33
 */
public interface FreightMapper extends BaseMapper<FreightEntity> {

    /**
     * 展示运费
     * @return
     * @throws DataAccessException
     */
    List<FreightBO> findFreightAll() throws DataAccessException;
}
