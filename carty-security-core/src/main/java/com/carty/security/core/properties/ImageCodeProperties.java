package com.carty.security.core.properties;

/**
 * 图片验证码配置类
 * @author Administrator
 *
 */
public class ImageCodeProperties extends SmsCodeProperties{

	//验证码图片宽度
	private int width = 67;
	//验证码图片高度
	private int height = 23;
	
	public ImageCodeProperties(){
		setLength(4);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
