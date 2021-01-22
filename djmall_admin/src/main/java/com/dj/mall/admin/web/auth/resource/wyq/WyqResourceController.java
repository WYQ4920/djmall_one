package com.dj.mall.admin.web.auth.resource.wyq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.WyqResourceApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.admin.vo.resource.ResourceVOReq;
import com.dj.mall.admin.vo.resource.ResourceVOResp;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
     * left页面权限控制展示
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("resourceShow")
    public ResultModel<Object> resourceShow(HttpSession session) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute(UserConstant.USER_SESSION);
        List<ResourceDTO> resList = user.getResourceList();
        List<ResourceDTO> list = new ArrayList<>();
        for (ResourceDTO res:resList) {
            if (UserConstant.RESOURCE_TYPE.equals(res.getResourceType())) {
                list.add(res);
            }
        }
        return new ResultModel<>().success(DozerUtil.mapList(list, ResourceVOResp.class));
    }

    /**
     * 展示资源
     */
    @GetMapping("list")
    @RequiresPermissions("WYQ_RESOURCE_MANAGER")
    public ResultModel show() throws Exception {
        List<ResourceDTO> resourceDTOList = wyqResourceApi.findResourceAll();
        return new ResultModel().success(DozerUtil.mapList(resourceDTOList, ResourceVOResp.class));
    }

    /**
     * 新增资源
     */
    @PostMapping("add")
    @RequiresPermissions("WYQ_RESOURCE_ADD_BTN")
    public ResultModel add(ResourceVOReq resourceVOReq) throws BusinessException {
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
    @RequiresPermissions("WYQ_RESOURCE_UPDATE_BTN")
    public ResultModel update(ResourceVOReq resourceVOReq) throws BusinessException {
        Assert.hasText(resourceVOReq.getResourceName(), "资源名不能为空");
        Assert.hasText(resourceVOReq.getUrl(), "资源PATH不能为空");
        Assert.hasText(resourceVOReq.getResourceCode(), "资源编码不能为空");
        Assert.hasText(resourceVOReq.getResourceType(), "资源类型不能为空");
        wyqResourceApi.updateResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

    /**
     * 删除资源
     */
    @PostMapping("del")
    @RequiresPermissions("WYQ_RESOURCE_DEL_BTN")
    public ResultModel del(ResourceVOReq resourceVOReq) throws Exception {
        wyqResourceApi.delResource(DozerUtil.map(resourceVOReq, ResourceDTO.class));
        return new ResultModel().success();
    }

}
