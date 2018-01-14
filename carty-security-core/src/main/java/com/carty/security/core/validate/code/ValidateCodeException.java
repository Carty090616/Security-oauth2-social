package com.carty.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 图片验证码异常
 * AuthenticationException是所有认证过程中异常的基类
 * @author Administrator
 *
 */
public class ValidateCodeException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
