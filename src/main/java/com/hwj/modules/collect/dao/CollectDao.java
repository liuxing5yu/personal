/**
 * 
 */
package com.hwj.modules.collect.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.collect.model.CollectModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:00 
 *
 */
@MyBatisRepository
public interface CollectDao {

	List<CollectModel> search(Map<String, Object> map);

	PageList<CollectModel> search(Map<String, Object> map, PageBounds pageBounds);

	void add(Map<String, Object> map);

	void changeStatus(Map<String, Object> map);

	/**
	 * 
	 *
	 * @param id
	 */
	void delete(String id);

}
