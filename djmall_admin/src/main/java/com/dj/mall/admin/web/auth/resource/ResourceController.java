package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.res.ResourceApi;
import com.dj.mall.auth.api.user.UserApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.admin.vo.resource.ResourceVOReq;
import com.dj.mall.admin.vo.resource.ResourceVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.CacheKeyConstant;
import com.dj.mall.common.constant.UserConstant;
import com.dj.mall.common.util.DozerUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * left页面权限控制展示
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("resourceShow")
    public ResultModel<Object> resourceShow(HttpSession session) throws Exception {
        UserDTO user = (UserDTO) session.getAttribute(UserConstant.USER_SESSION);
        //List<ResourceDTO> resList = user.getResourceList();
        // 从缓存中取
        HashOperations hashOperations = redisTemplate.opsForHash();
        List<ResourceDTO> resList = (List<ResourceDTO>) hashOperations.get(CacheKeyConstant.ROLE_ALL,CacheKeyConstant.ROLE_ID_PRE+user.getRoleId());
        List<ResourceDTO> list = new ArrayList<>();
        for (ResourceDTO res:resList) {
            if (UserConstant.RESOURCE_TYPE.equals(res.getResourceType())) {
                list.add(res);
            }
        }
        return new ResultModel<>().success(DozerUtil.mapList(list, ResourceVOResp.class));
    }

    /**
     * 资源展示
     *
     * @param resourceVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("showResZtree")
    @RequiresPermissions("RESOURCE_MANAGER")
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
    @RequiresPermissions("RESOURCE_ADD_BTN")
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
    @RequiresPermissions("RESOURCE_UPDATE_BTN")
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
    @RequiresPermissions("RESOURCE_DEL_BTN")
    public ResultModel<Object> del(ResourceVOReq resourceVOReq) throws Exception {
        ResourceDTO resourceDTO = DozerUtil.map(resourceVOReq, ResourceDTO.class);
        resourceApi.delRes(resourceDTO.getResourceIds());
        return new ResultModel().success();
    }


}
