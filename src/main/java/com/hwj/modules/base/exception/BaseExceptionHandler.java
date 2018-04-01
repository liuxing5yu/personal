/**
 * 
 */
package com.hwj.modules.base.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hwj.modules.base.entity.ResultEntity;
import com.hwj.modules.base.entity.ResultEntityHashMapImpl;

/**
 * 
 *
 * @author hwj
 * @date 2018年2月27日 下午6:41:56 
 *
 */
@ControllerAdvice
public class BaseExceptionHandler {

	/**
	 * 统一处理Controller中的异常
	 * 
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultEntity> handle(Exception e) {
		e.printStackTrace();

		ResultEntity result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
