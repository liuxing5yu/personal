package com.hwj.modules.user.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;
import com.hwj.modules.base.util.StringUtil;
import com.hwj.modules.user.model.MenuNode;
import com.hwj.modules.user.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userView(final HttpServletRequest request, final HttpServletResponse response, Model model) {
		return "/user/user_menu";
	}

	@RequestMapping(value = "/getAllMenu", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> getMenus(final HttpServletRequest request, final HttpServletResponse response) {
		ResultEntity result = null;
		try {
			Map<String, Object> map = menuService.findAllMenus();

			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "查询菜单成功");
			if (map.get("root") != null) {
				MenuNode root = (MenuNode) map.get("root");
				result.addObject("menu", root);

			}
			if (map.get("list") != null) {
				@SuppressWarnings("unchecked")
				List<MenuNode> list = (List<MenuNode>) map.get("list");
				result.addObject("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		return new ResponseEntity<ResultEntity>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> addMenu(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody MenuNode menuNode) {
		ResultEntity result = null;
		try {
			String id = StringUtil.createUUID();
			menuNode.setId(id);
			MenuNode m = new MenuNode();
			int i = menuService.addMenu(menuNode);
			if (i > 0) {
				m = menuService.findMenuById(id);
				result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "增加菜单成功");
				result.addObject("menu", m);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		return new ResponseEntity<ResultEntity>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> updateMenu(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody MenuNode menuNode) {
		ResultEntity result = null;
		try {
			MenuNode m = new MenuNode();
			int i = menuService.updateMenu(menuNode);
			if (i > 0) {
				m = menuService.findMenuById(menuNode.getId());
				List<MenuNode> list = new ArrayList<MenuNode>();
				list.add(m);
				Map<String, Object> map = menuService.find(list, m);

				result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "修改菜单成功");
				if (map.get("root") != null) {
					MenuNode root = (MenuNode) map.get("root");
					result.addObject("menu", root);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		return new ResponseEntity<ResultEntity>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/deleteMenu", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> deleteMenu(final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable String id) {
		ResultEntity result = null;
		try {
			menuService.deleteMenu(id);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "删除菜单成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		return new ResponseEntity<ResultEntity>(result, HttpStatus.OK);
	}

}
