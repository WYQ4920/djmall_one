package com.dj.mall.auth.web.res;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.ResourceVOReq;
import com.dj.mall.auth.vo.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("resourceShow")
    public ResultModel<Object> resourceShow() throws Exception {
        List<ResourceDTO> list = resourceApi.findAll();
        List<ResourceVOResp> list1 = DozerUtil.mapList(list, ResourceVOResp.class);
        return new ResultModel<>().success(list1);
    }

    /**
     * 资源展示
     * @param resourceVOReq
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

    /**
     * 查重
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping ("checkResourceName")
    public Boolean checkResourceName(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        return  resourceApi.findByResourceName(resourceDTO.getResourceName());
    }

    /**
     * 新增
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping ("add")
    public ResultModel<Object> add(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        resourceDTO.setResourceCode(resourceVOReq.getResourceCode().toUpperCase());
        resourceApi.addRes(resourceDTO);
        return new ResultModel<>().success();
    }

    /**
     * 修改
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PutMapping("update")
    public ResultModel<Object> update(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        resourceApi.updateRes(resourceDTO);
        return new ResultModel<>().success();
    }





}
