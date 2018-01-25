/**
 * 
 */
package com.hwj.modules.collect.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwj.modules.base.util.MapUtil;
import com.hwj.modules.collect.dao.CollectTagDao;
import com.hwj.modules.collect.model.CollectTagModel;
import com.hwj.modules.collect.model.param.CollectTagParamModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:41 
 *
 */
@Service
public class CollectTagService {

	@Autowired
	private CollectTagDao dao;

	public List<CollectTagModel> search(CollectTagParamModel param) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, param);
		return dao.search(map);
	}

	/**
	 * 
	 *
	 * @param model
	 */
	public void add(CollectTagParamModel model) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, model);
		dao.add(map);
	}

	/**
	 * 
	 *
	 * @param id
	 * @param status
	 */
	public void changeStatus(String id, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("status", status);
		dao.changeStatus(map);
	}

	/**
	 * 
	 *
	 * @param id
	 */
	public void delete(String id) {
		dao.delete(id);
	}

}
