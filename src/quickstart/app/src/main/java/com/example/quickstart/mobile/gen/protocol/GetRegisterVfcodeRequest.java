package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 获取注册验证码.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class GetRegisterVfcodeRequest extends MobileMessage {


	private String phonenumber;

	public GetRegisterVfcodeRequest() {
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