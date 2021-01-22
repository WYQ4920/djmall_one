package com.dj.mall.admin.web.freight;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.freight.FreghtResp;
import com.dj.mall.admin.vo.freight.FreightReq;
import com.dj.mall.common.base.BusinessException;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.freight.FreightApi;
import com.dj.mall.dict.dto.freight.FreightDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/21 22:23
 */

@RestController
@RequestMapping("/freight/")
public class FreightController {

    @Reference
    private FreightApi freightApi;

    /**
     * 展示运费
     * @return
     */
    @GetMapping("show")
    @RequiresPermissions("FREIGHT_MANAGER")
    public ResultModel show() throws Exception {
        List<FreightDTO> freightDTOList = freightApi.findFreightAll();
        return new ResultModel().success(DozerUtil.mapList(freightDTOList, FreghtResp.class));
    }

    /**
     * 新增运费
     * @return
     */
    @PostMapping("add")
    @RequiresPermissions("FREIGHT_ADD_BTN")
    public ResultModel add(FreightReq freightReq) throws BusinessException {
        Assert.hasText(freightReq.getFreight(), "运费不能为空");
        freightApi.addFreight(DozerUtil.map(freightReq, FreightDTO.class));
        return new ResultModel().success();
    }

    /**
     * 修改运费
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("FREIGHT_UPDATE_BTN")
    public ResultModel update(FreightReq freightReq) throws BusinessException {
        Assert.hasText(freightReq.getFreight(), "运费不能为空");
        freightApi.updateFreight(DozerUtil.map(freightReq, FreightDTO.class));
        return new ResultModel().success();
    }

}
