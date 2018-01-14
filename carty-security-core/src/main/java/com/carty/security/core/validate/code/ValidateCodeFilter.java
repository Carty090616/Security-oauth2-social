package com.carty.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.carty.security.core.properties.SecurityProperties;

/**
 * 图片验证码过滤器，会在UsernamePasswordAuthenticationFilter之前执行
 * 验证成功则会调用UsernamePasswordAuthenticationFilter
 * 不成功则会抛出异常
 * OncePerRequestFilter时spring提供的工具类，保证这个过滤器只执行一次
 * @author Administrator
 *
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	//存储需要拦截的URL
	private Set<String> urls = new HashSet<>();
	
	private SecurityProperties securityProperties;
	//spring提供的工具类，用与判断URL是否匹配
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		//获取配置文件中配置的需要拦截的URL
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}
		//登陆请求的URL
		urls.add("/authentication/form");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		boolean action = false;
		//循环判断请求的URL是否与配置文件中的URL匹配
		for (String url : urls) {
			if(pathMatcher.match(url, request.getRequestURI())){
				action = true;
			}
		}
		
		if(action){
			
			try {
				//验证请求传入的验证码和生成的验证码是否匹配
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		//调用下一个过滤器
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		
		//从session中取出验证码
		ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request,
				ValidateCodeController.SESSION_KEY);
		
		//从请求中取出验证码
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		
		if(StringUtils.isBlank(codeInRequest)){
			throw new ValidateCodeException("验证码的值不能为空");
		}
		
		if(codeInSession == null){
			throw new ValidateCodeException("验证码不存在");
		}
		
		if(codeInSession.isExpried()){
			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("验证码已过期");
		}
		
		if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)){
			throw new ValidateCodeException("验证码不匹配");
		}
		
		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SessionStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
