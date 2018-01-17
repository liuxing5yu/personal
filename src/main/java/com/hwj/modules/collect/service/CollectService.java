/**
 * 
 */
package com.hwj.modules.collect.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
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
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, param);

		List<CollectModel> list = dao.search(map);

		// 生成PageList
		int fromIndex = 0, toIndex = list.size();
		int pageStart = param.getPageStart(), pageEnd = param.getPageStart() + param.getPageSize();
		if (fromIndex < pageStart) {
			fromIndex = pageStart;
		}
		if (toIndex > pageEnd) {
			toIndex = pageEnd;
		}
		PageList<CollectModel> pageList = new PageList<>(list.subList(fromIndex, toIndex),
				new Paginator(param.getPageNow(), param.getPageSize(), list.size()));

		return pageList;
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

		if (!CollectionUtils.isEmpty(model.getTagIds())) {
			addTags(collectId, model.getTagIds());
		}
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

	/**
	 * 
	 *
	 * @param id
	 */
	public void delete(String id) {
		dao.delete(id);
	}

	/**
	 * 
	 *
	 * @param model
	 */
	@Transactional
	public void modifyTags(CollectParamModel param) {
		// 清除收藏的所有标签
		collectCollectTagDao.delete(param.getId());

		// 为收藏添加选中的标签
		Map<String, Object> map = new HashMap<>();
		map.put("collectId", param.getId());
		map.put("collectTagIds", param.getTagIds());
		collectCollectTagDao.add(map);
	}
}
