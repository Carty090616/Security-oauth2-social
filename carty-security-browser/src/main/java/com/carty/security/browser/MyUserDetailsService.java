package com.carty.security.browser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义的UserDetailsService
 * @author Administrator
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * 通过此方法可以从数据库（或者别的存储方式）中获取用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登陆用户名为：" + username);
		
		//根据用户名获取用户信息
		String password = passwordEncoder.encode("123456");
		//AuthorityUtils 此工具类可以将一个以逗号分隔的字符串转换为权限（测试使用）
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("admain");
		//以下四种boolean的判断逻辑，可根据自身的业务是否需要进行业务逻辑的判断，如果不需要设置为永远true就可以
		//根据查找到的用户信息判断用户是是否过期
		boolean isAccountNonExpired = true;
		//判断账户有没有被锁定
		boolean isAccountNonLocked = true;
		//判断密码是否过期
		boolean isCredentialsNonExpired = true;
		//判断是否可用（账户有没有被删除）
		boolean isEnabled = true;
		
		return new User(username, 
				password, 
				isEnabled, 
				isAccountNonExpired, 
				isCredentialsNonExpired, 
				isAccountNonLocked, 
				auth);
	}

}
