package com.dj.mall.auth.web.res;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.WyqResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.resource.ResourceVOReq;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/15 22:00
 * 资源表 -> 控制层
 */

@RestController
@RequestMapping("/auth/resource/wyq/")
public class WyqResourceController {

    @Reference
    private WyqResourceApi wyqResourceApi;

    /**
     * 展示资源
     * @return
     * @throws Exception
     */
    @GetMapping("list")
    public ResultModel show() throws Exception {
        List<ResourceDTO> resourceDTOList = wyqResourceApi.findResourceAllByName();
        return new ResultModel().success(DozerUtil.mapList(resourceDTOList, ResourceVOResp.class));
    }

    /**
     * 新增资源
     */
    @PostMapping("add")
    public ResultModel add(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        Assert.hasText(resourceVOReq.getUrl(), "资源PATH不能为空");
        Assert.hasText(resourceVOReq.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOReq.getResourceType(), "资源类型不能为空");
        wyqResourceApi.addResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

    /**
     * 修改资源
     */
    @PutMapping("update")
    public ResultModel update(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        Assert.hasText(resourceVOReq.getUrl(), "资源PATH不能为空");
        Assert.hasText(resourceVOReq.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOReq.getResourceType(), "资源类型不能为空");
        wyqResourceApi.updateResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

   /* *//**
     * 删除资源
     */
    @PostMapping("del")
    public ResultModel del(ResourceVOReq resourceVOReq) throws Exception {
        wyqResourceApi.delResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

}
