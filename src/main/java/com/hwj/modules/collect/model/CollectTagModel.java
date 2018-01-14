package com.hwj.modules.collect.model;

import java.util.Date;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:15 
 *
 */
public class CollectTagModel {
	private String id;
	private Date lastModifyTime;
	private String name;
	private String isValid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}
