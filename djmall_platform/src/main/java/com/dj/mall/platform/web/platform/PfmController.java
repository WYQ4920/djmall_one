package com.dj.mall.platform.web.platform;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.common.base.ResultModel;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.platform.vo.product.ProductVOReq;
import com.dj.mall.product.api.ProductApi;
import com.dj.mall.product.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/djmall_platform/")
@RestController
public class PfmController {

    @Reference
    private ProductApi productApi;

    /**
     * 展示商品
     * @param productVOReq
     * @return
     */
    @GetMapping("list")
    public ResultModel list(ProductVOReq productVOReq, Integer pageNo) throws Exception {

        ProductDTO list = productApi.findProductAll(DozerUtil.map(productVOReq, ProductDTO.class), pageNo);
        return new ResultModel().success(list);

    }




}
