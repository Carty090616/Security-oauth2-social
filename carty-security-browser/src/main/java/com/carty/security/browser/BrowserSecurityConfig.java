package com.carty.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()//表单登陆
//		http.httpBasic()//表示security提供的默认登录方式，会有一个弹框弹出登陆
			.and()
			.authorizeRequests()//表示以下的配置都是授权的配置
			.anyRequest()//表示所有请求
			.authenticated();//表示都需要身份认证
	}
	
	/**
     * 注册一个密码加密器
     * BCryptPasswordEncoder 实现了PasswordEncoder接口
     * 
     * PasswordEncoder接口中的encode()方法用于加密（每次登录时都会被调用，对密码进行加密）
     *                      matches()方法是供security调用，此方法会将登录时输入的密码（加密后）和数据库中的密码进行比对
     * 
     * 在用户注册时，先加密密码再存入数据库中
     * 
     * 也可以自己定义一个实现PasswordEncoder接口的加密类
     * @return
     */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
