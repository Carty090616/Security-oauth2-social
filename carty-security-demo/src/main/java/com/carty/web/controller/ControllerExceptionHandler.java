package com.carty.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.carty.exception.UserNotExistException;

/**
 * 控制器的异常处理--负责处理其他控制器抛出的异常
 * @author Administrator
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * 表示处理UserNotExistException
	 * @return
	 */
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//定义返回的状态码
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex){
		Map<String, Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
	
}
