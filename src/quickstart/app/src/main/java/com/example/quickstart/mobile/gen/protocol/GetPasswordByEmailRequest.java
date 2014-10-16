package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 邮箱找回密码.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class GetPasswordByEmailRequest extends MobileMessage {


	private String email;

	public GetPasswordByEmailRequest() {
		super();
	}	

	/**
	 * @return 邮件地址
	 */
	@Length(max = 20,min = 1)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}