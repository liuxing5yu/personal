/**
 * 
 */
package com.hwj.modules.collect.model.param;

import com.hwj.modules.base.entity.PaginationParam;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:08 
 *
 */
public class CollectParamModel extends PaginationParam {

	private static final long serialVersionUID = -666964339182327040L;

	private String title;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
