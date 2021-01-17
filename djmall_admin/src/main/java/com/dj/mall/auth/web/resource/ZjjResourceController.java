package com.dj.mall.auth.web.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ZjjResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.ResourceVOReq;
import com.dj.mall.auth.vo.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dj
 */
@RestController
@RequestMapping("/zjj/resource/")
public class ZjjResourceController {

    @Reference
    private ZjjResourceApi zjjResourceApi;

    /**
     * 资源展示
     * @return
     * @throws Exception
     */
    @GetMapping("list")
    public ResultModel list() throws Exception{
        List<ResourceDTO> resourceList = zjjResourceApi.findResource();
        return new ResultModel().success(DozerUtil.mapList(resourceList, ResourceVOResp.class));
    }

    @PostMapping("add")
    public ResultModel add(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(),"资源名不能为空");
        Assert.hasText(resourceVOReq.getResourceCode(),"资源编码不能为空");
        Assert.hasText(resourceVOReq.getResourceType(),"资源类型不能为空");
        zjjResourceApi.addResource(DozerUtil.map(resourceVOReq,ResourceDTO.class));
        return new ResultModel().success();
    }







}
