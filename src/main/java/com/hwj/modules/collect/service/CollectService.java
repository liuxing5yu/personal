/**
 * 
 */
package com.hwj.modules.collect.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MapUtil;
import com.hwj.modules.collect.dao.CollectCollectTagDao;
import com.hwj.modules.collect.dao.CollectDao;
import com.hwj.modules.collect.model.CollectModel;
import com.hwj.modules.collect.model.param.CollectParamModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:41 
 *
 */
@Service
public class CollectService {

	@Autowired
	private CollectDao dao;

	@Autowired
	private CollectCollectTagDao collectCollectTagDao;

	public PageList<CollectModel> search(CollectParamModel param) {
		PageBounds pageBounds = new PageBounds(param.getPageNow() + 1, param.getPageSize());
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, param);
		return dao.search(map, pageBounds);
	}

	/**
	 * 
	 *
	 * @param model
	 */
	@Transactional
	public void add(CollectParamModel model) {
		// 添加收藏
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, model);
		String collectId = UUID.randomUUID().toString();
		map.put("id", collectId);
		dao.add(map);

		addTags(collectId, model.getTagIds());
	}

	public void addTags(String collectId, List<String> tagIds) {
		Map<String, Object> map = new HashMap<>();
		map.put("collectId", collectId);
		map.put("collectTagIds", tagIds);

		collectCollectTagDao.add(map);
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

}
