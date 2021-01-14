package com.dj.mall.auth.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/")
public class IndexController {

	@RequestMapping("/toIndex")
	public String toIndex(String token,ModelMap map) {
		map.put("token", token);
		return "index/index";
	}

	@RequestMapping("toTop")
	public String toTop() {

		return "index/top";
	}

	@RequestMapping("toLeft")
	public String toLeft(String token,ModelMap map) {
		map.put("token", token);
		return "index/left";
	}

	@RequestMapping("toRight")
	public String toRight() throws Exception {
		return "index/right";
	}



}