package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 手机找回密码.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class GetPasswordByPhoneRequest extends MobileMessage {


	private String phonenumber;

	public GetPasswordByPhoneRequest() {
		super();
	}	

	/**
	 * @return 手机号码
	 */
	@Length(max = 20,min = 1)
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}