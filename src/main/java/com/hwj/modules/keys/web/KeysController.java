/**
 * 
 */
package com.hwj.modules.keys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		ModelAndView mv = new ModelAndView("/keys");

		return mv;
	}

	@RequestMapping("/search")
	public ResponseEntity<ResultEntity> search(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody KeysParam param) {
		ResultEntity result = null;
		try {
			PageList<KeysModel> list = service.search(param);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "成功");
			result.addObject("data", list);
			result.addObject("recordsTotal", list.getPaginator().getTotalCount());
			result.addObject("recordsFiltered", list.getPaginator().getTotalCount());
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "联系管理员");
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
