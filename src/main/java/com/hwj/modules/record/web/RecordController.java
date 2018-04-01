/**
 * 
 */
package com.hwj.modules.record.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 *
 * @author hwj
 * @date 2018年2月28日 上午10:48:34 
 *
 */
@Controller
@RequestMapping("/record")
public class RecordController {
	@RequestMapping("/view")
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView("/record/record");
		return mv;
	}
}
