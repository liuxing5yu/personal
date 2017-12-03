/**
 * 
 */
package com.hwj.modules.keys.dao;

import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.keys.model.KeysModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月2日 下午4:11:48 
 *
 */
@MyBatisRepository
public interface KeysDao {

	PageList<KeysModel> search(Map<String, Object> map, PageBounds pageBounds);

	void addOne(Map<String, Object> map);

}
