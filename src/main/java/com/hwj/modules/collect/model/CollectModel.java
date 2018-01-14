package com.hwj.modules.collect.model;

import java.util.List;

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
	private List<CollectTagModel> tags;

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

	public List<CollectTagModel> getTags() {
		return tags;
	}

	public void setTags(List<CollectTagModel> tags) {
		this.tags = tags;
	}

}
