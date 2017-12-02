/**
 * 
 */
package com.hwj.modules.keys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
		return dao.search();
	}

}
