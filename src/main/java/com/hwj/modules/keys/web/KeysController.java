/**
 * 
 */
package com.hwj.modules.keys.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;
import com.hwj.modules.keys.model.KeysModel;
import com.hwj.modules.keys.model.param.KeysParam;
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
	public ResponseEntity<ResultEntity> search(@RequestBody KeysParam param) {
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> add(HttpServletRequest request, @RequestBody KeysModel model) {
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

}
