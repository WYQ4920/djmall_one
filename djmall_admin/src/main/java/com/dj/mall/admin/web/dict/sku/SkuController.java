package com.dj.mall.admin.web.dict.sku;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.DictVOReq;
import com.dj.mall.admin.vo.dict.DictVOResp;
import com.dj.mall.admin.vo.dict.sku.SkuVOReq;
import com.dj.mall.admin.vo.dict.sku.SkuVOResp;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.sku.SkuApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/24 21:00
 */
@RestController
@RequestMapping("/sku/")
public class SkuController {

    @Reference
    private SkuApi skuApi;

    /**
     * sku展示
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("show")
    public ResultModel<Object> show(String productType) throws Exception {
        List<SkuDTO> list = skuApi.findByProductType(productType);
        return new ResultModel<>().success(DozerUtil.mapList(list, SkuVOResp.class));

    }

    /**
     * upd
     * @param skuVOReq
     * @return
     * @throws Exception
     */
    @PostMapping("update")
    public ResultModel<Object> updateSku(SkuVOReq skuVOReq) throws Exception{
        skuApi.updateSku(DozerUtil.map(skuVOReq, SkuDTO.class));
        return new ResultModel<>().success();
    }

}
