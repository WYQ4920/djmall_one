package com.dj.mall.auth.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.common.base.ResultModel;
import org.springframework.web.bind.annotation.GetMapping;

import com.dj.mall.auth.api.ResourceApi;
import com.dj.mall.auth.dto.ResourceDTO;
import com.dj.mall.common.base.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("resourceShow")
    public ResultModel<Object> resourceShow(ResourceDTO resourceDTO){
        return  resourceApi.findAll(resourceDTO);
    }





}
