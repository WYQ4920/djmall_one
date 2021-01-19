package com.dj.mall.auth.web.res;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.auth.vo.resource.ResourceVOReq;
import com.dj.mall.auth.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import org.apache.catalina.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @Reference(check = false)
    private UserApi userApi;

    /**
     * left页面权限控制展示
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("resourceShow")
    public ResultModel<Object> resourceShow(HttpSession session) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<ResourceDTO> resList = userApi.getUserResource(user.getId());
        return new ResultModel<>().success(resList);
    }

    /**
     * 资源展示
     *
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
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("checkResourceName")
    public Boolean checkResourceName(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        return resourceApi.findByResourceName(resourceDTO.getResourceName());
    }

    /**
     * 新增
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("add")
    public ResultModel<Object> add(ResourceVOReq resourceVOReq) throws Exception {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        resourceDTO.setResourceCode(resourceVOReq.getResourceCode().toUpperCase());
        resourceApi.addRes(resourceDTO);
        return new ResultModel<>().success();
    }

    /**
     * 修改
     *
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

    /**
     * del
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("/del")
    public ResultModel<Object> del(ResourceVOReq resourceVOReq) throws Exception {
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        resourceApi.delRes(resourceDTO.getResourceIds());
        return new ResultModel().success();
    }


}
