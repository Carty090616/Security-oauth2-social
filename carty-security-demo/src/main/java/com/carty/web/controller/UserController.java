package com.carty.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carty.dto.User;
import com.carty.dto.UserQueryCondition;
import com.carty.exception.UserNotExistException;

@RestController
public class UserController {

	/**
	 * 获取全部用户
	 */
	@RequestMapping("/all")
	public List<User> queryAll(){
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setUsername("carty");
		list.add(user);
		return list;
	}
	
	/**
	 * 查询请求
	 * @RequestParam 映射请求参数到java方法的参数
	 */
	@GetMapping("/user")
	public List<User> query(@RequestParam String username){
		System.out.println("*****" + username + "*****");
		List<User> list = new ArrayList<>();
		list.add(new User());
		list.add(new User());
		list.add(new User());
		return list;
	}
	
	/**
	 * 查询请求(将多个参数封装到一个类中)
	 * ReflectionToStringBuilder 工具类，用于将对象信息打印在控制台
	 */
	@GetMapping("/userMoreParam")
	public List<User> queryMoreParam(UserQueryCondition user){
		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
		List<User> list = new ArrayList<>();
		list.add(new User());
		list.add(new User());
		list.add(new User());
		return list;
	}
	
	/**
	 * 获取用户信息
	 *@PathVariable 将restful风格的URL传回的参数映射大java方法的参数中
	 */
	@GetMapping("/user/{id}")
	public User getInfo( String id){
		System.out.println("*****" + id + "*****");
		User user = new User();
		user.setUsername("carty");
		System.out.println(user.toString());
		return user;
	}
	
	/**
	 * 获取用户信息，并在URL中用正则表达式判断是否是数字
	 *@PathVariable 将restful风格的URL传回的参数映射大java方法的参数中
	 */
	@GetMapping("/user02/{id:\\d+}")
	public User getInfo02( String id){
		System.out.println("*****" + id + "*****");
		User user = new User();
		user.setUsername("carty");
		return user;
	}
	
	@PostMapping("/create")
	public User create(@RequestBody User user){
		
		System.out.println(user.getId());
		System.out.println(user.getPassword());
		System.out.println(user.getUsername());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	} 
	
	/**
	 * 测试异常处理机制
	 *@PathVariable 将restful风格的URL传回的参数映射大java方法的参数中
	 */
	@GetMapping("/user03/{id:\\d+}")
	public User getInfo03(@PathVariable("id") String id){
		
		throw new UserNotExistException(id);
	}
	
	/**
	 * 测试filter
	 *@PathVariable 将restful风格的URL传回的参数映射大java方法的参数中
	 */
	@GetMapping("/user04/{id:\\d+}")
	public User getInfo04(@PathVariable("id") String id){
		System.out.println("进入了服务");
		User user = new User();
		user.setUsername("carty");
		return user;
	}
}
