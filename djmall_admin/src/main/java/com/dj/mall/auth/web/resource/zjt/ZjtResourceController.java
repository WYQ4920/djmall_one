package com.dj.mall.auth.web.resource.zjt;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ZjtResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.resource.ResourceVOReq;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zjt/")
public class ZjtResourceController {

    @Reference
    private ZjtResourceApi zjtResourceApi;

    /**
     * 展示
     *
     * @return
     */
    @GetMapping("resourceShow")
    public ResultModel<Object> resourceShow() throws Exception {
        List<ResourceDTO> list = zjtResourceApi.findResourceAll();
        return new ResultModel<Object>().success(DozerUtil.mapList(list, ResourceVOResp.class));
    }

    /**
     * 资源新增
     *
     * @param resourceVOResp
     * @return
     */
    @PostMapping("add")
    public ResultModel<Object> addResourceAdd(ResourceVOResp resourceVOResp) throws Exception {
        Assert.hasText(resourceVOResp.getResourceName(), "资源名称不能为空");
        Assert.hasText(resourceVOResp.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOResp.getResourceType(), "资源类型不能为空");
        Assert.hasText(resourceVOResp.getUrl(), "资源PATH 不能为空");
        zjtResourceApi.addResourceAdd(DozerUtil.map(resourceVOResp, ResourceDTO.class));
        return new ResultModel<>().success();
    }

    /**
     * 修改资源
     *
     * @param resourceVOResp
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public ResultModel<Object> update(ResourceVOResp resourceVOResp) throws Exception {
        Assert.hasText(resourceVOResp.getResourceName(), "资源名称不能为空");
        Assert.hasText(resourceVOResp.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOResp.getResourceType(), "资源类型不能为空");
        Assert.hasText(resourceVOResp.getUrl(), "资源PATH 不能为空");
        zjtResourceApi.update(DozerUtil.map(resourceVOResp, ResourceDTO.class));
        return new ResultModel<>().success();
    }

    /**
     * 删除资源
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("del")
    public ResultModel<Object> del(ResourceVOReq resourceVOReq) throws Exception {
        zjtResourceApi.del(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel<>().success();
    }
}
