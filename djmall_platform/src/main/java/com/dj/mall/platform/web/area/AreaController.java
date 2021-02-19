package com.dj.mall.platform.web.area;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.constant.CacheConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.order.api.area.AreaApi;
import com.dj.mall.order.dto.area.AreaDTO;
import com.dj.mall.platform.vo.area.AreaVOResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/4 13:59
 */
@RestController
@RequestMapping("/areas/")
public class AreaController {

    @Reference
    private AreaApi areaApi;

    @PostMapping("show")
    public ResultModel<Object> showLinked(Integer parentId) throws Exception{

        List<AreaDTO> list = areaApi.findByParentId(parentId);
        return new ResultModel<>().success(DozerUtil.mapList(list, AreaVOResp.class));

    }

}
