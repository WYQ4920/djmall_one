package com.dj.mall.auth.task;

import com.dj.mall.auth.api.role.RoleApi;
import com.dj.mall.auth.dto.res.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.common.constant.CacheKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZJJ
 * @date 2021/1/26 20:57
 */
@Component
@Slf4j
public class InitRoleCacheTask {

    @Autowired
    private RoleApi roleApi;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 就绪事件
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        //System.out.println("=========init========");
        log.info("=========init role cache start========");
        //初始化全部的角色资源 放入redis内
        try {
            //全部角色
            List<RoleDTO> roleList = roleApi.getRoleList();

            HashOperations hashOperations = redisTemplate.opsForHash();
            for (RoleDTO role : roleList) {
                //获取角色对应的资源集合
                List<ResourceDTO> resourceList = roleApi.getRoleResource(role.getId());
                // 存redis
                hashOperations.put(CacheKeyConstant.ROLE_ALL,CacheKeyConstant.ROLE_ID_PRE+role.getId(),resourceList);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            log.error(e.getMessage(), e);
        }

        log.info("=========init role cache end========");
    }
}
