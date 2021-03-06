/**
 * 
 */
package com.hwj.modules.keys.model.param;

import com.hwj.modules.base.entity.PaginationParam;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月2日 下午4:12:22 
 *
 */
public class KeysParamModel extends PaginationParam {

	private static final long serialVersionUID = 1L;

	private String key;
	private String desc;

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

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

}
