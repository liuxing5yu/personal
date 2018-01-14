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
public class CollectTagParamModel extends PaginationParam {

	private static final long serialVersionUID = -1171154886369297592L;

	private String isValid;
	private String name;

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
