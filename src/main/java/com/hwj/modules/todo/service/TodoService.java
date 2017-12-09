/**
 * 
 */
package com.hwj.modules.todo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<TodoModel> search(TodoParamModel param) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, param);
		return dao.search(map);
	}

}
