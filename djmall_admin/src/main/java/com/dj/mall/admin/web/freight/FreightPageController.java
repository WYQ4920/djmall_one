package com.dj.mall.admin.web.freight;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.mall.common.constant.DictsConstant;
import com.dj.mall.common.util.DozerUtil;
import com.dj.mall.dict.api.DictApi;
import com.dj.mall.dict.api.freight.FreightApi;
import com.dj.mall.dict.dto.DictDTO;
import com.dj.mall.dict.dto.freight.FreightDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author WYQ
 * @Date 2021/1/21 21:30
 */

@Controller
@RequestMapping("/freight/")
public class FreightPageController {

    @Reference
    private DictApi dictApi;

    @Reference
    private FreightApi freightApi;

    /**
     * 去展示运费
     * @return
     */
    @RequestMapping("toShow")
    @RequiresPermissions("FREIGHT_MANAGER")
    public String toShow(ModelMap map) throws Exception {
        List<DictDTO> dictDTOList = dictApi.findDictByCode(DictsConstant.DICT_CODE);
        map.put("dictData", DozerUtil.mapList(dictDTOList, DictDTO.class));
        return "/freight/freight_list";
    }

    /**
     * 去修改运费
     * @return
     */
    @RequestMapping("toUpdate")
    @RequiresPermissions("FREIGHT_UPDATE_BTN")
    public String toUpdate(Integer id, ModelMap map) throws Exception {
        FreightDTO freightList = freightApi.findFreightById(id);
        map.put("list", DozerUtil.map(freightList, FreightDTO.class));
        return "/freight/freight_update";
    }

}
