package com.dj.mall.admin.web.dict.sku.page;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.sku.SkuVOResp;
import com.dj.mall.dict.api.attr.ProductAttrApi;
import com.dj.mall.dict.api.sku.SkuApi;
import com.dj.mall.dict.dto.attr.ProductAttrDTO;
import com.dj.mall.dict.dto.sku.SkuDTO;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Author zhengyk
 * @Date 2021/1/24 20:59
 */
@Controller
@RequestMapping("/sku/")
public class SkuPageController {

    @Reference(check = false)
    private SkuApi skuApi;

    @Reference(check = false)
    private ProductAttrApi productAttrApi;

    @RequestMapping("toShow")
    public String toShow() throws Exception {
        return "sku/show";
    }

    @RequestMapping("toUpdate/{code}")
    public String toUpdate(@PathVariable String code, Model model) throws Exception {
        List<SkuDTO> list1 = skuApi.findByCode(code);
        model.addAttribute("list1", list1);

        SkuVOResp voResp = new SkuVOResp();
        for (SkuDTO sd : list1) {
            voResp.setProductType(sd.getProductType());
            break;
        }
        model.addAttribute("voResp", voResp);

        List<ProductAttrDTO> list = productAttrApi.findProductAttrAll();

        model.addAttribute("list", list);

        return "sku/update";
    }

}
