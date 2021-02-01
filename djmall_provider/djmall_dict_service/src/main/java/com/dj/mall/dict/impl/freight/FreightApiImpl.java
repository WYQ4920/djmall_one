package com.dj.mall.dict.impl.freight;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.constant.DictsConstant;
import com.dj.mall.common.constant.SystemConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.freight.FreightApi;
import com.dj.mall.dict.bo.freight.FreightBO;
import com.dj.mall.dict.dto.freight.FreightDTO;
import com.dj.mall.dict.entity.freight.FreightEntity;
import com.dj.mall.dict.mapper.freight.FreightMapper;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:33
 */

@Service
public class FreightApiImpl extends ServiceImpl<FreightMapper, FreightEntity> implements FreightApi {

    /**
     * 展示运费
     * @return
     * @throws Exception
     */
    @Override
    public List<FreightDTO> findFreightAll() throws Exception {
        List<FreightBO> freightEntityList = getBaseMapper().findFreightAll();
        return DozerUtil.mapList(freightEntityList, FreightDTO.class);
    }

    /**
     * 新增运费
     * @param freightDTO 运费信息
     * @throws Exception
     */
    @Override
    public void addFreight(FreightDTO freightDTO) throws BusinessException {
        QueryWrapper<FreightEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_code", freightDTO.getDictCode());
        FreightEntity one = super.getOne(queryWrapper);
        if (null != one) {
            throw new BusinessException("已有该物流公司运费信息");
        }
        if (DictsConstant.pinkage == freightDTO.getFreight()) {
            freightDTO.setFreight("包邮");
        }
        super.save(DozerUtil.map(freightDTO, FreightEntity.class));
    }

    /**
     * 获取修改的运费信息
     * @param id 修改id
     * @return
     * @throws Exception
     */
    @Override
    public FreightDTO findFreightById(Integer id) throws Exception {
        QueryWrapper<FreightEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return DozerUtil.map(super.getOne(queryWrapper), FreightDTO.class);
    }

    /**
     * 修改运费
     * @param freightDTO 运费信息
     * @throws Exception
     */
    @Override
    public void updateFreight(FreightDTO freightDTO) throws BusinessException {
        FreightEntity freightEntity = super.getById(freightDTO.getId());
        if (null != freightEntity && !freightDTO.getId().equals(freightEntity.getId())) {
            throw new BusinessException("已有该物流公司运费信息");
        }
        if (DictsConstant.pinkage == freightDTO.getFreight()) {
            freightDTO.setFreight("包邮");
        }
        super.updateById(DozerUtil.map(freightDTO, FreightEntity.class));
    }

    /**
     * 查询邮费
     * @return
     * @throws Exception
     */
    @Override
    public List<FreightDTO> findFreightAndDict() throws Exception {
        List<FreightBO>  freightList = getBaseMapper().findFreightAndDict();
        return DozerUtil.mapList(freightList, FreightDTO.class);
    }

}
