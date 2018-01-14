package com.carty.security.core.properties;

/**
 * 封装几种验证方式：图形验证码、短信验证码
 * @author Administrator
 *
 */
public class ValidateCodeProperties {

	private ImageCodeProperties image = new ImageCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
}
