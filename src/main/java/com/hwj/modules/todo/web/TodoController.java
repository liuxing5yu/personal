/**
 * 
 */
package com.hwj.modules.todo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;
import com.hwj.modules.todo.model.TodoModel;
import com.hwj.modules.todo.model.param.TodoParamModel;
import com.hwj.modules.todo.service.TodoService;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:30 
 *
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService service;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> search(@RequestBody TodoParamModel param) {
		ResultEntity result = null;
		try {
			List<TodoModel> list = service.search(param);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "成功");
			result.addObject("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
