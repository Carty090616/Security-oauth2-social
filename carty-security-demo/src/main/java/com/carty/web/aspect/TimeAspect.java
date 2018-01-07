package com.carty.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component//使用时需要注释@Component
public class TimeAspect {

	/**
     * @Around中的表达式
     * 第一个*表示任何返回类型
     * com.carty.web.controller.UserController.* 表示这个类中的所有方法
     * (..)表示任何参数类型
     */
	@Around("execution(* com.carty.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pj) throws Throwable{

		System.out.println("time aspect start");
		//获取传递来的参数值

		Object[] arr = pj.getArgs();
		for (Object object : arr) {
			System.out.println("arg is "+object);
		}
		long start = new Date().getTime();

		//表示继续调用被拦截的方法

		Object obj = pj.proceed();

		System.out.println("aspect 耗时："+(new Date().getTime() - start));

		System.out.println("time aspect end");

		return obj;
	}

}
