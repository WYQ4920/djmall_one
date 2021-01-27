package com.dj.mall.dict.task;

import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ZJJ
 * @date 2021/1/27 15:23
 */
@Component
@Slf4j
public class InitDictCacheTask {

    @Autowired
    private DictApi dictApi;

    @Autowired
    private RedisTemplate redisTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("===============init dict start===============");
        //获取全部字典数据
        try {
            List<DictDTO> dictAllList = dictApi.findAllDict();
            // 存入redis
            HashOperations hashOperations = redisTemplate.opsForHash();
            for (DictDTO dict : dictAllList) {
                hashOperations.put(dict.getParentCode(),dict.getCode(),dict.getDictName());
            }

        } catch (Exception e) {
            //e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        log.info("===============init dict end===============");
    }
}
