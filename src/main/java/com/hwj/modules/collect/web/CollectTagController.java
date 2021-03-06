/**
 * 
 */
package com.hwj.modules.collect.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;
import com.hwj.modules.collect.model.CollectTagModel;
import com.hwj.modules.collect.model.param.CollectTagParamModel;
import com.hwj.modules.collect.service.CollectTagService;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:30 
 *
 */
@Controller
@RequestMapping("/collectTag")
public class CollectTagController {

	@Autowired
	private CollectTagService service;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> search(@RequestBody CollectTagParamModel param) {
		ResultEntity result = null;
		try {
			List<CollectTagModel> list = service.search(param);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "成功");
			result.addObject("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> add(@RequestBody CollectTagParamModel model) {
		ResultEntity result = null;
		try {
			service.add(model);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> delete(@PathVariable String id) {
		ResultEntity result = null;
		try {
			service.delete(id);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
