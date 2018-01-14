package com.carty.security.core.properties;

/**
 * 图片验证码配置类
 * @author Administrator
 *
 */
public class ImageCodeProperties {

	//验证码图片宽度
	private int width = 67;
	//验证码图片高度
	private int height = 23;
	//验证码的位数
	private int length = 4;
	//验证码的默认过期时间
	private int expireIn = 60;
	//配置哪些请求需要图形验证码
	private String url;
	
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
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
