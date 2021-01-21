package com.dj.mall.auth.web.resource.zjj;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ZjjResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.vo.resource.ResourceVOReq;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
     *
     * @return
     * @throws Exception
     */
    @GetMapping("list")
    public ResultModel list() throws Exception {
        List<ResourceDTO> resourceList = zjjResourceApi.findResource();
        return new ResultModel().success(DozerUtil.mapList(resourceList, ResourceVOResp.class));
    }

    /**
     * 新增资源
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ResultModel add(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        Assert.hasText(resourceVOReq.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOReq.getResourceType(), "资源类型不能为空");
        zjjResourceApi.addResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

    @PutMapping("update")
    public ResultModel update(ResourceVOReq resourceVOReq) throws Exception {
        zjjResourceApi.updateResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

    /**
     * 删除资源
     *
     * @param resourceVOReq
     * @return
     */
    @DeleteMapping("del")
    public ResultModel delResource(ResourceVOReq resourceVOReq) throws Exception {
        zjjResourceApi.delResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }


}
