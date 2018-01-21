package com.carty.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.carty.security.browser.authentication.CartyAuthenctiationFailureHandler;
import com.carty.security.browser.authentication.CartyAuthenticationSuccessHandler;
import com.carty.security.core.authentication.AbstractChannelSecurityConfig;
import com.carty.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.carty.security.core.properties.SecurityConstants;
import com.carty.security.core.properties.SecurityProperties;
import com.carty.security.core.validate.code.ValidateCodeFilter;
import com.carty.security.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig{

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	/**
	 * 记住我功能的token持久化配置
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		//配置数据源
		tokenRepository.setDataSource(dataSource);
		//在系统初始化时会自动创建存储的表
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//表单登陆的配置
		applyPasswordAuthenticationConfig(http);
		
		http
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(validateCodeSecurityConfig)
				.and()
			.rememberMe()
				//配置token持久化
				.tokenRepository(persistentTokenRepository())
				//配置失效时间
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				//配置userDetailsService
				.userDetailsService(userDetailsService)
				.and()
			.authorizeRequests()//表示以下的配置都是授权的配置
				.antMatchers(
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						securityProperties.getBrowser().getLoginPage(),
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll()//表示这个URL不需要身份认证
				.anyRequest()//表示所有请求
				.authenticated()//表示都需要身份认证
				.and()
			.csrf().disable();//关闭csrf
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
