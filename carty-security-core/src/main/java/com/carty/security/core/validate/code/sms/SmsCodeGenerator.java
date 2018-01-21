package com.carty.security.core.validate.code.sms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.carty.security.core.properties.SecurityProperties;
import com.carty.security.core.validate.code.ValidateCode;
import com.carty.security.core.validate.code.ValidateCodeGenerator;

/**
 * 短信验证码实现类
 * @author Administrator
 *
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 生成图形验证码的方法
	 * @param request
	 * @return
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		//RandomStringUtils 随机数生成工具类，参数表示需要生成的位数
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
	

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
