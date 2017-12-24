/**
 * 
 */
package com.hwj.modules.codelist.dao;

import java.util.List;
import java.util.Map;

import com.hwj.modules.base.util.MyBatisRepository;
import com.hwj.modules.codelist.model.CodelistModel;

/**
 * 
 *
 * @author hwj
 * @date 2017年12月9日 下午3:58:00 
 *
 */
@MyBatisRepository
public interface CodelistDao {

	/**
	 * 
	 *
	 * @param map
	 * @return
	 */
	List<CodelistModel> get(Map<String, Object> map);

}
