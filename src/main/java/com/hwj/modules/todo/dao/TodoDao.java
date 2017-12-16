/**
 * 
 */
package com.hwj.modules.todo.dao;

import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.todo.model.TodoModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:00 
 *
 */
@MyBatisRepository
public interface TodoDao {

	PageList<TodoModel> search(Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 
	 *
	 * @param map
	 */
	void add(Map<String, Object> map);

	/**
	 * 
	 *
	 * @param id
	 * @param status
	 */
	void changeStatus(Map<String, Object> map);

}
