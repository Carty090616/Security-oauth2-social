package com.carty.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * @author Administrator
 *
 */
public interface ValidateCodeProcessor {

	/**
	 * 校验码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";
	
	/**
	 * 创建校验码
	 * 
	 * ServletWebRequest是spring提供的工具类，封装类请求（request）和响应（response）
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;
	
	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);
}
