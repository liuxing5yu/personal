/**
 * 
 */
package com.hwj.modules.recentdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hwj.modules.base.util.MyUtils;
import com.hwj.modules.recentdo.dao.RecentDoMapper;
import com.hwj.modules.recentdo.model.RecentDo;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:41 
 *
 */
@Service
public class RecentDoService {
	@Autowired
	private RecentDoMapper dao;

	/**
	 * 
	 *
	 * @return
	 */
	public List<RecentDo> selectAll() {
		return dao.selectAll();
	}

	/**
	 * 
	 *
	 * @param model
	 */
	public void save(RecentDo model) {
		if (StringUtils.isEmpty(model.getId())) {
			model.setId(MyUtils.uuid());
			// add
			dao.insert(model);
		} else {
			// edit
			dao.updateByPrimaryKey(model);
		}

	}

	/**
	 * 
	 *
	 * @param id
	 * @return
	 */
	public RecentDo selectOne(String id) {
		return dao.selectByPrimaryKey(id);
	}

	/**
	 * 
	 *
	 * @param id
	 */
	public void delete(String id) {
		dao.deleteByPrimaryKey(id);
	}

}
