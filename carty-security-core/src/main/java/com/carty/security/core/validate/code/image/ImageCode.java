package com.carty.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.carty.security.core.validate.code.ValidateCode;

/**
 * 图片验证码的基础类
 * @author Administrator
 *
 */
public class ImageCode extends ValidateCode{

	//图片
	private BufferedImage image;
	
	//expireIn表示多少秒过期
	public ImageCode (BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}	
	
	public ImageCode (BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
