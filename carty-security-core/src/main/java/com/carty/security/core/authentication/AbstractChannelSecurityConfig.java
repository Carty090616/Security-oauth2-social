package com.carty.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.carty.security.core.properties.SecurityConstants;

public abstract class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler cartyAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler cartyAuthenticationFailureHandler;
	
	/**
	 * 配置表单登陆的配置
	 * @param http
	 * @throws Exception
	 */
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		
//		http.httpBasic()//表示security提供的默认登录方式，会有一个弹框弹出登陆
		http.formLogin()//表单登陆
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//配置登陆页
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//配置登陆登陆页的form表单的action属性值（用于的登陆的URL）
			.successHandler(cartyAuthenticationSuccessHandler)//配置登陆成功的handler
			.failureHandler(cartyAuthenticationFailureHandler);//配置登陆失败的handler
	}
}
