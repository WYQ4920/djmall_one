package com.dj.mall.auth.task;

import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.common.constant.CacheKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/26 21:56
 */

@Component
@Slf4j
public class WyqInitRoleCacheTask {

    @Autowired
    private RoleApi roleApi;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 项目启动完成事件
     */
    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        log.info("===============init role cache start===============");
        // 初始化全部角色、资源,存入redis内
        try {
            // 获取全部的角色
            List<RoleDTO> roleList = roleApi.getRoleList();
            for (RoleDTO role : roleList) {
                // 获取角色对应的所有资源
                List<ResourceDTO> resourceList = roleApi.getRoleResource(role.getId());
                // 存入redis -> ( hash: 固定key  角色id  List<资源> )
                HashOperations hashOperations = redisTemplate.opsForHash();
                hashOperations.put(CacheKeyConstant.WYQ_ROLE_ALL, CacheKeyConstant.WYQ_ROLE_ID_PRE + role.getId(), resourceList);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        log.info("===============init role cache end===============");
    }
}
