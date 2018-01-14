package com.carty.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carty.security.core.properties.SecurityProperties;

@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * @ConditionalOnMissingBean 表示在启动时，初始化ValidateCodeGenerator之前
	 * 会先在spring容器中寻找名字为imageCodeGenerator的bean
	 * 
	 * 这样写的好处是：在demo模块中可以重新配置一个图形验证码的生成器，
	 * 但是必须添加@Component("imageCodeGenerator")注解，而且注解中的名字必须是imageCodeGenerator
	 * 这样就可以保证别人在不修改你写的代码的情况下，完成系统的增量修改
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

}
