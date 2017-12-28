package com.hwj.modules.user.dao;

import java.util.List;

import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.user.model.MenuNode;

@MyBatisRepository
public interface MenuDao {

	public MenuNode getRootMenuNode();

	public List<MenuNode> getChildren(MenuNode root);

	public int addMenu(MenuNode menuNode);

	public MenuNode findMenuById(String id);

	public int updateMenu(MenuNode menuNode);

	public int deleteMenu(String id);

	public List<MenuNode> getShowChildren(MenuNode root);

	public List<MenuNode> findMenuByPermiss(String permissId);

}
