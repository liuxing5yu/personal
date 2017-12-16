/**
 * 
 */
package com.hwj.modules.todo.model.param;

import com.hwj.modules.base.entity.PaginationParam;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:08 
 *
 */
public class TodoParamModel extends PaginationParam {

	private static final long serialVersionUID = 1L;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
