package com.hwj.modules.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hwj.modules.user.model.MenuNode;
import com.hwj.modules.user.service.MenuService;

@Controller
public class SysController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/")
	public String index(final HttpServletRequest request, final HttpServletResponse response, Model model) {
		MenuNode root = menuService.findShowMenus();
		if (root.getNodes() != null && root.getNodes().size() > 0) {
			List<MenuNode> menus = root.getNodes();
			model.addAttribute("menus", menus);
		}
		return "/user/index";
	}

	@RequestMapping(value = "/dashboard")
	public String dashboard(final HttpServletRequest request, final HttpServletResponse response, Model model) {
		return "/user/dashboard";
	}
}
