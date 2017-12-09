/**
 * 
 */
package com.hwj.modules.todo.dao;

import java.util.List;
import java.util.Map;

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

	List<TodoModel> search(Map<String, Object> map);

}
