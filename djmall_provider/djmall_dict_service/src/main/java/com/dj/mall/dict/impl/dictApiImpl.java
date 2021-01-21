package com.dj.mall.dict.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.entity.DictEntity;
import com.dj.mall.dict.mapper.DictMapper;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 15:02
 */
@Service
public class dictApiImpl extends ServiceImpl<DictMapper, DictEntity> implements DictApi {


    /**
     * 展示dict 通过code查
     *
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public List<DictDTO> findByCode(String code) throws Exception {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code).or().eq("parent_code", code);
        List<DictEntity> list = super.list(queryWrapper);
        return DozerUtil.mapList(list, DictDTO.class);
    }

    /**
     * 查重
     *
     * @param dictName
     * @return
     * @throws Exception
     */
    @Override
    public Boolean findBydictName(String dictName) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dict_name", dictName);
        DictEntity dictEntity = super.getOne(queryWrapper);
        return dictEntity == null? true:false;
    }

    /**
     * add dict
     *
     * @param dictDTO
     * @throws Exception
     */
    @Override
    public void addDict(DictDTO dictDTO) throws Exception {
        DictEntity dictEntity = DozerUtil.map(dictDTO, DictEntity.class);
        Boolean aBoolean = this.findBydictName(dictEntity.getDictName());
        if (aBoolean) {
            super.save(dictEntity);
            return;
        }
        throw new BusinessException("重名");
    }

    /**
     * to upd
     *
     * @param code
     * @return
     */
    @Override
    public DictDTO findByUpdCode(String code) {
        QueryWrapper<DictEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code);
        DictEntity dictEntity = super.getOne(queryWrapper);
        return DozerUtil.map(dictEntity, DictDTO.class);
    }

    /**
     * @param dictDTO
     */
    @Override
    public void updateDict(DictDTO dictDTO) throws BusinessException {
        DictEntity dictEntity = DozerUtil.map(dictDTO, DictEntity.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dict_name", dictEntity.getDictName());
        DictEntity dictEntity1 = super.getOne(queryWrapper);
        if(null != dictEntity1 && !dictEntity.getCode().equals(dictEntity.getCode())){
            throw new BusinessException("重名");
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        DictEntity dictEntity2 = new DictEntity();
        dictEntity2.setDictName(dictEntity.getDictName());
        queryWrapper1.eq("code", dictEntity.getCode());
        super.update(dictEntity2, queryWrapper1);
    }
}
