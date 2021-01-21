package com.dj.mall.auth.web.dict;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.dict.DictVOReq;
import com.dj.mall.auth.vo.dict.DictVOResp;
import com.dj.mall.auth.vo.resource.ResourceVOReq;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 11:29
 */
@RestController
@RequestMapping("/dict/")
public class DictController {

    @Reference
    private DictApi dictApi;

    /**
     * 字典展示
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("show")
    public ResultModel<Object> show(String parentCode) throws Exception {
        List<DictDTO> list = dictApi.findByCode(parentCode);
        List<DictVOResp> list1 = DozerUtil.mapList(list, DictVOResp.class);
        return new ResultModel<>().success(list1);
    }

    /**
     * 查重名
     * @param dictName
     * @return
     * @throws Exception
     */
    @PostMapping("checkDictName")
    public Boolean checkDictName(String dictName) throws Exception{
        Boolean aBoolean = dictApi.findBydictName(dictName);
        return aBoolean;
    }

    /**
     * 查重code
     * @param code
     * @return
     * @throws Exception
     */
    @PostMapping("checkCode")
    public Boolean checkCode(String code) throws Exception{
        List<DictDTO> list = dictApi.findByCode(code);
        if(list.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * ADD DICT
     */
    @PostMapping("add")
    public ResultModel<Object> addDict(DictVOReq dictVOReq) throws Exception{
        dictApi.addDict(DozerUtil.map(dictVOReq, DictDTO.class));
        return new ResultModel<>().success();
    }

    /**
     * upd dict
     * @param dictVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public ResultModel<Object> updateDict(DictVOReq dictVOReq) throws Exception{
        dictApi.updateDict(DozerUtil.map(dictVOReq, DictDTO.class));
        return new ResultModel<>().success();
    }


}
