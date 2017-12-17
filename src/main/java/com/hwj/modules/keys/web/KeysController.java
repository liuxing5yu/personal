/**
 * 
 */
package com.hwj.modules.keys.web;

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
import com.hwj.modules.keys.model.KeysModel;
import com.hwj.modules.keys.model.param.KeysParamModel;
import com.hwj.modules.keys.service.KeysService;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月2日 下午2:26:07 
 *
 */

@Controller
@RequestMapping("/keys")
public class KeysController {

	@Autowired
	private KeysService service;

	@RequestMapping("/view")
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView("/keys/keys");
		return mv;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> search(@RequestBody KeysParamModel param) {
		ResultEntity result = null;
		try {
			PageList<KeysModel> list = service.search(param);
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

	@RequestMapping(value = "/checkExist", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> checkExist(@RequestBody KeysModel model) {
		ResultEntity result = null;
		try {
			int num = service.checkExist(model.getKey());
			if (num > 0) {
				result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "快捷键已存在");
			} else {
				result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "快捷键未存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> add(@RequestBody KeysModel model) {
		ResultEntity result = null;
		try {
			service.addOne(model);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> edit(@RequestBody KeysModel model) {
		ResultEntity result = null;
		try {
			service.edit(model);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "修改成功");
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

	@RequestMapping(value = "/clearTable", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> clearTable() {
		ResultEntity result = null;
		try {
			service.clearTable();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "清表成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
