package com.hwj.modules.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwj.modules.user.dao.MenuDao;
import com.hwj.modules.user.model.MenuNode;
import com.hwj.modules.user.model.State;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public MenuNode findShowMenus() {
		MenuNode root = menuDao.getRootMenuNode();
		find(root);
		return root;
	}

	public Map<String, Object> findAllMenus() {
		MenuNode root = menuDao.getRootMenuNode();
		State s = new State();
		s.setSelected(true);
		root.setState(s);
		List<MenuNode> list = new ArrayList<MenuNode>();
		list.add(root);
		Map<String, Object> map = find(list, root);
		return map;
	}

	public Map<String, Object> find(List<MenuNode> list, MenuNode root) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MenuNode> childrens = menuDao.getChildren(root);
		if (childrens != null && childrens.size() > 0) {
			root.setNodes(childrens);
			for (MenuNode menu : childrens) {
				list.add(menu);
				find(list, menu);
			}

		}
		map.put("root", root);
		map.put("list", list);

		return map;
	}

	public void find(MenuNode root) {
		List<MenuNode> childrens = menuDao.getShowChildren(root);
		if (childrens != null && childrens.size() > 0) {
			root.setNodes(childrens);
			for (MenuNode menu : childrens) {
				find(menu);
			}
		}

	}

	public int addMenu(MenuNode menuNode) {
		return menuDao.addMenu(menuNode);
	}

	public MenuNode findMenuById(String id) {
		return menuDao.findMenuById(id);
	}

	public int updateMenu(MenuNode menuNode) {
		return menuDao.updateMenu(menuNode);
	}

	public int deleteMenu(String id) {
		return menuDao.deleteMenu(id);
	}

}
