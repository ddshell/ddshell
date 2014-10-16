package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 用户登录.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class LoginRequest extends MobileMessage {


	private String idcardno;
	private String password;

	public LoginRequest() {
		super();
	}	

	/**
	 * @return 身份证号
	 */
	@Length(max = 20,min = 1)
	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	/**
	 * @return 登录密码
	 */
	@Length(max = 20,min = 1)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}