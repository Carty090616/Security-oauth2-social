package com.carty.web.controller;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	/**
	 * 标注了 @Before 注解的方法会在每一个测试用例执行前执行
	 */
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	/**
	 * 测试查询请求
	 * @throws Exception 
	 */
	@Test
	public void whenQuerySuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user")//模拟发出get请求
				//设置请求携带的参数
				.param("username", "carty")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())//设置期望的返回结果的状态码
				//设置期望返回的集合长度是3
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}
	
	/**
	 * 测试查询请求（将多个参数封装到一个类中）
	 * @throws Exception 
	 */
	@Test
	public void whenQuerySuccess02() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/userMoreParam")//模拟发出get请求
				//设置请求携带的参数
				.param("username", "carty")
				.param("age", "18")
				.param("ageTo", "60")
				.param("xxx", "yyy")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())//设置期望的返回结果的状态码
				//设置期望返回的集合长度是3
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}
	
	/**
	 * 测试获取用户信息
	 * @throws Exception 
	 */
	@Test
	public void whenGetInfoSuccess() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/1")//模拟发出get请求
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())//设置期望的返回结果的状态码
				//设置期望返回的内容
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("carty"));
	}
	
	/**
	 * 测试获取用户信息,并在URL中用正则表达式判断
	 * @throws Exception 
	 */
	@Test
	public void whenGetInfoFail() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user02/a")//模拟发出get请求
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());//设置期望的返回结果的状态码
	}
	
	/**
	 * 测试创建用户
	 * @throws Exception 
	 */
	@Test
	public void whenCreateSuccess() throws Exception{
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/create")//模拟发出get请求
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())//设置期望的返回结果的状态码
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
}











