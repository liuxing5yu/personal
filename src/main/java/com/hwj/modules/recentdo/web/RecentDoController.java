/**
 * 
 */
package com.hwj.modules.recentdo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.util.MyUtils;
import com.hwj.modules.recentdo.model.RecentDo;
import com.hwj.modules.recentdo.service.RecentDoService;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:30 
 *
 */
@Controller
@RequestMapping("/recentdo")
public class RecentDoController {

	@Autowired
	private RecentDoService service;

	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> selectAll() {
		return MyUtils.success(service.selectAll());
	}

	@RequestMapping(value = "/selectOne/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> selectOne(@PathVariable("id") String id) {
		return MyUtils.success(service.selectOne(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> save(@RequestBody RecentDo model) {
		service.save(model);
		return MyUtils.success("添加成功");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> delete(@PathVariable String id) {
		service.delete(id);
		return MyUtils.success("删除成功");
	}
}
