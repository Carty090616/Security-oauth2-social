package com.carty.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.carty.security.core.properties.SecurityProperties;

/**
 * 图形验证码实现类
 * @author Administrator
 *
 */
public class ImageCodeGenerator implements ValidateCodeGenerator{
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 生成图形验证码的方法
	 * @param request
	 * @return
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", getSecurityProperties().getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", getSecurityProperties().getCode().getImage().getHeight());
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		
		Random random = new Random();
		//生成干扰的条纹
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		
		//生成4为随机数，并将随机数写入图片中
		String sRand = "";
		for (int i = 0; i < getSecurityProperties().getCode().getImage().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}
		
		g.dispose();
		//60表示失效的秒数
		return new ImageCode(image, sRand, getSecurityProperties().getCode().getImage().getExpireIn());
	}
	
	/**
	 * 生成随机背景条纹
	 * @param i
	 * @param j
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if(fc > 255){
			fc = 255;
		}
		if(bc > 255){
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
