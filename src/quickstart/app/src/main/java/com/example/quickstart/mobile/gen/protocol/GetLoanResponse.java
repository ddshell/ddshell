package com.example.quickstart.mobile.gen.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 获取借款详情.服务端响应
 * 
 * @author 代码生成器v1.0
 * 
 */
@JsonInclude(Include.NON_NULL)
public class GetLoanResponse extends MobileMessage {


	private String amount;
	private String applytime;
	private String idcardno;
	private String interestrate;
	private String phonenumber;
	private String realname;
	private String statustext;
	private String term;

	public GetLoanResponse() {
		super();
	}

	public GetLoanResponse(String statusCode, String statusMessage) {
		this.setStatusCode(statusCode);
		this.setStatusMessage(statusMessage);
	}


	/**
	 * @return 借款金额
	 */
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 申请时间
	 */
	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	/**
	 * @return 身份证号
	 */
	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	/**
	 * @return 借款利率
	 */
	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	/**
	 * @return 手机号码
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return 真实姓名
	 */
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return 借款状态
	 */
	public String getStatustext() {
		return statustext;
	}

	public void setStatustext(String statustext) {
		this.statustext = statustext;
	}

	/**
	 * @return 借款期限
	 */
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}