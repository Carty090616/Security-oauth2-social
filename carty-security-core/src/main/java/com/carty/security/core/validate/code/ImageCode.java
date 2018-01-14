package com.carty.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码的基础类
 * @author Administrator
 *
 */
public class ImageCode {

	//图片
	private BufferedImage image;
	//验证码
	private String code;
	//失效时间
	private LocalDateTime expireTime;
	
	//expireIn表示多少秒过期
	public ImageCode (BufferedImage image, String code, int expireIn){
		this.image = image;
		this.code = code;
		//表示当前时间加上秒数，然后生成一个新的时间，这个时间就表示验证码失效的时间
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}	
	
	public ImageCode (BufferedImage image, String code, LocalDateTime expireTime){
		this.image = image;
		this.code = code;
		this.expireTime = expireTime;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
}
