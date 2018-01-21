package com.carty.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

	/**
	 * 生成验证码的方法
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
}
