/**
 * 
 */
package com.hwj.modules.collect.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;
import com.hwj.modules.collect.model.CollectModel;
import com.hwj.modules.collect.model.param.CollectParamModel;
import com.hwj.modules.collect.service.CollectService;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:30 
 *
 */
@Controller
@RequestMapping("/collect")
public class CollectController {

	@Autowired
	private CollectService service;

	@RequestMapping("/view")
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView("/collect/collect");
		return mv;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> search(@RequestBody CollectParamModel param) {
		ResultEntity result = null;
		try {
			PageList<CollectModel> list = service.search(param);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "成功");
			result.addObject("data", list);
			result.addObject("recordsTotal", list.getPaginator().getTotalCount());
			result.addObject("recordsFiltered", list.getPaginator().getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> add(@RequestBody CollectParamModel model) {
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

	@RequestMapping(value = "/changeStatus/{id}/{status}", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> changeStatus(@PathVariable String id, @PathVariable String status) {
		ResultEntity result = null;
		try {
			service.changeStatus(id, status);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "成功");
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

	@RequestMapping(value = "/modifyTags", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> modifyTags(@RequestBody CollectParamModel model) {
		ResultEntity result = null;
		try {
			service.modifyTags(model);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
