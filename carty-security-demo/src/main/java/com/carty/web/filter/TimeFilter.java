package com.carty.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * 记录所有请求的处理时间Filter
 * @author Administrator
 *
 */
//@Component//使用时需要注释@Component
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		//开始处理时间
		long start = new Date().getTime();
		//过滤器链，调用下一个过滤器
		chain.doFilter(request, response);
		//结束处理时间
		long end = new Date().getTime();
		System.out.println("time filter 耗时:" + (end - start));
		System.out.println("time filter end");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
	}

}
