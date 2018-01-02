package com.hwj.modules.collect.model;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:15 
 *
 */
public class CollectModel {
	private String id;
	private String title;
	private String url;
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
