/**
 * 
 */
package com.hwj.modules.collect.dao;

import java.util.Map;

import com.hwj.modules.base.util.MyBatisRepository;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:00 
 *
 */
@MyBatisRepository
public interface CollectCollectTagDao {

	void add(Map<String, Object> map);

}
