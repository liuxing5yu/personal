/**
 * 
 */
package com.hwj.modules.codelist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwj.modules.base.util.MapUtil;
import com.hwj.modules.codelist.dao.CodelistDao;
import com.hwj.modules.codelist.model.CodelistModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:41 
 *
 */
@Service
public class CodelistService {

	@Autowired
	private CodelistDao dao;

	/**
	 * 
	 *
	 * @param model
	 * @return
	 */
	public List<CodelistModel> get(CodelistModel model) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, model);
		return dao.get(map);
	}

}
