package com.carty.security.core.validate.code.sms;

public class DefaultSmsCodeSender implements SmsCodeSender{

	@Override
	public void send(String phone, String code) {
		// TODO Auto-generated method stub
		System.out.println("向手机"+phone+"发送短信验证码"+code);
	}

}
