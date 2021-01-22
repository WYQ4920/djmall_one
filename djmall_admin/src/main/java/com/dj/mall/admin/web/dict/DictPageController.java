package com.dj.mall.admin.web.dict;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.dict.DictVOResp;
import com.dj.mall.common.constant.DictsConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.dto.DictDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zhengyk
 * @Date 2021/1/21 11:29
 */
@Controller
@RequestMapping("/dict/")
public class DictPageController {

    @Reference(check = false)
    private DictApi dictApi;

    @RequestMapping("toShow")
    public String toShow(Model model) throws Exception {
        List<DictDTO> dictList = dictApi.findByCode (DictsConstant.CODE);
        model.addAttribute("dictList", DozerUtil.mapList(dictList, DictVOResp.class));
        return "dict/show";
    }

    @RequestMapping("toUpdate/{code}")
    public String toUpdate(@PathVariable String code, Model model) throws Exception {
        DictDTO dictDTO = dictApi.findByUpdCode(code);
        model.addAttribute("dictDTO", DozerUtil.map(dictDTO, DictVOResp.class));
        return "dict/update";
    }




}
