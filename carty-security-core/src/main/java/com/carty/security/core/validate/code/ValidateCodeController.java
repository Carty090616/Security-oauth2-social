package com.carty.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.carty.security.core.properties.SecurityProperties;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	//操作session的工具类
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private ValidateCodeGenerator imageCodeGenerator;
	
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));
		//第一个参数表示把请求传进去，sessionStrategy会自动从请求中取出session
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		//第一个参数表示验证码的图片
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
	
}
