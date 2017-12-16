/**
 * 
 */
package com.hwj.modules.todo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MapUtil;
import com.hwj.modules.todo.dao.TodoDao;
import com.hwj.modules.todo.model.TodoModel;
import com.hwj.modules.todo.model.param.TodoParamModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:41 
 *
 */
@Service
public class TodoService {

	@Autowired
	private TodoDao dao;

	public PageList<TodoModel> search(TodoParamModel param) {
		PageBounds pageBounds = new PageBounds(param.getPageNow() + 1, param.getPageSize());
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, param);
		return dao.search(map, pageBounds);
	}

	/**
	 * 
	 *
	 * @param model
	 */
	public void add(TodoParamModel model) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, model);
		dao.add(map);
	}

	/**
	 * 
	 *
	 * @param id
	 * @param status
	 */
	public void changeStatus(String id, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("status", status);
		dao.changeStatus(map);

	}

}
