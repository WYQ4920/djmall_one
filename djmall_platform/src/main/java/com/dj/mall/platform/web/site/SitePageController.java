package com.dj.mall.platform.web.site;

import com.dj.mall.common.constant.ProductConstant;
import com.dj.mall.dict.dto.DictDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/site/")
public class SitePageController {

    @RequestMapping("toSite")
    public String toOrder(HttpServletRequest httpServletRequest, Model model) throws Exception {
        return "site/list";
    }


}
