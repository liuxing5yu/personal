/**
 * 
 */
package com.hwj.modules.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;

/**
 * 
 *
 * @author hwj
 * @date 2018年2月27日 下午5:13:27 
 *
 */
public class MyUtils {

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 返回成功的响应结果，无返回
	 * 
	 *
	 * @return
	 */
	public static ResponseEntity<ResultEntity> success(String msg) {
		return success(msg, null);
	}

	/**
	 * 返回成功的响应结果，单个返回
	 * 
	 *
	 * @return
	 */
	public static ResponseEntity<ResultEntity> success(Object obj) {
		return success("成功", obj);
	}

	/**
	 * 返回成功的响应结果，单个返回
	 * 
	 *
	 * @return
	 */
	public static ResponseEntity<ResultEntity> success(String msg, Object obj) {
		Map<String, Object> map = new HashMap<>();
		map.put("data", obj);
		return success(msg, map);
	}

	/**
	 * 返回成功的响应结果，多个返回
	 * 
	 * 
	 *
	 * @return
	 */
	public static ResponseEntity<ResultEntity> success(Map<String, Object> map) {
		return success("成功", map);
	}

	/**
	 * 返回成功的响应结果，多个返回
	 * 
	 *
	 * @return
	 */
	public static ResponseEntity<ResultEntity> success(String msg, Map<String, Object> map) {
		ResultEntity result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, msg);
		if (!CollectionUtils.isEmpty(map)) {
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				result.addObject(key, map.get(key));
			}
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

}
