package com.dj.mall.auth.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.auth.vo.ResourceVOReq;
import com.dj.mall.auth.vo.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.web.bind.annotation.GetMapping;

import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.common.base.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/14 14:58
 */
@RestController
@RequestMapping("/res/")
public class ResourceController {


    @Reference(check = false)
    private ResourceApi resourceApi;


    @PostMapping("resourceShow")
    public ResultModel<Object> resourceShow(ResourceDTO resourceDTO) throws Exception {
        List<ResourceDTO> list = resourceApi.findAll(resourceDTO);
        List<ResourceVOResp> list1 = DozerUtil.mapList(list, ResourceVOResp.class);
        return new ResultModel<>().success(list1);
    }

    /**
     * 资源展示
     * @param ResourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("showResZtree")
    public ResultModel<Object> showResZtree(ResourceVOReq resourceVOReq) throws Exception {
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        List<ResourceDTO> list = resourceApi.findAll1(resourceDTO);
        List<ResourceVOResp> list1 = DozerUtil.mapList(list, ResourceVOResp.class);
        return new ResultModel<>().success(list1);
    }



}
