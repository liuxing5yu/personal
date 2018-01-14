/**
 * 
 */
package com.hwj.modules.collect.dao;

import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.collect.model.CollectTagModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:00 
 *
 */
@MyBatisRepository
public interface CollectTagDao {

	PageList<CollectTagModel> search(Map<String, Object> map);

	/**
	 * 
	 *
	 * @param map
	 */
	void add(Map<String, Object> map);

	/**
	 * 
	 *
	 * @param id
	 * @param status
	 */
	void changeStatus(Map<String, Object> map);

}
