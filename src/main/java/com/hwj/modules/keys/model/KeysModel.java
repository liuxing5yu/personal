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
	private String id;
	private String key;
	private String desc;
	private String rawDesc;
	private String scene;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}
}
