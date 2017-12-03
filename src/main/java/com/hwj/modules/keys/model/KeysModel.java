/**
 * 
 */
package com.hwj.modules.keys.model;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月2日 下午4:12:05 
 *
 */
public class KeysModel {
	private String key;
	private String desc;
	private String rawDesc;

	public KeysModel() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRawDesc() {
		return rawDesc;
	}

	public void setRawDesc(String rowDesc) {
		this.rawDesc = rowDesc;
	}
}
