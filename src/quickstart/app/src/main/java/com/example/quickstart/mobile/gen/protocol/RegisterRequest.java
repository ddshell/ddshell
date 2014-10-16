package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 用户注册.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class RegisterRequest extends MobileMessage {


	private String email;
	private String idcardno;
	private String password;
	private String phonenumber;
	private String realname;
	private String vfcode;

	public RegisterRequest() {
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

	/**
	 * @return 真实姓名
	 */
	@Length(max = 20,min = 1)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return 验证码
	 */
	@Length(max = 20,min = 1)
	public String getVfcode() {
		return vfcode;
	}

	public void setVfcode(String vfcode) {
		this.vfcode = vfcode;
	}
}