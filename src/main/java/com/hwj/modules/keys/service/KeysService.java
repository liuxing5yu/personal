/**
 * 
 */
package com.hwj.modules.keys.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MapUtil;
import com.hwj.modules.keys.dao.KeysDao;
import com.hwj.modules.keys.model.KeysModel;
import com.hwj.modules.keys.model.param.KeysParam;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月2日 下午4:11:24 
 *
 */
@Service
public class KeysService {

	@Autowired
	private KeysDao dao;

	public PageList<KeysModel> search(KeysParam param) {
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
	public void addOne(KeysModel model) {
		Map<String, Object> map = new HashMap<>();
		MapUtil.object2Map(map, model);
		dao.addOne(map);
	}

	/**
	 * 
	 *
	 */
	public void clearTable() {
		dao.clearTable();
	}

}
