package com.carty.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 记录所有请求的处理时间Filter
 * @author Administrator
 *
 */
//@Component//使用时需要注释@Component
public class TimeInterceptor implements HandlerInterceptor {

	/**
     * 请求处理之后调用（无论是否有抛出异常都会调用 ）
     */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		long start = (long) request.getAttribute("startTime");
		//参数ex表示调用方法抛出的异常
		System.out.println("ex is"+ex);
		System.out.println("time interceptor耗时："+(new Date().getTime() - start));
	}

	/**
     * 请求处理之后调用（如果请求抛出异常则不调用）
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
		long start = (long) request.getAttribute("startTime");
		System.out.println("time interceptor耗时："+(new Date().getTime() - start));
	}

	/**
     * 
     * 请求处理之前调用
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle");
		//获取将要调用的类
		System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
		//获取将要调用的方法
		System.out.println(((HandlerMethod) handler).getMethod().getName());
		//向request中添加属性
		request.setAttribute("startTime", new Date().getTime());
		//返回false则不会调用方法，返回true怎会调用
		return true;
	}

}

