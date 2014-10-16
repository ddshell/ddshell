package com.example.quickstart.mobile.gen.protocol;

import org.hibernate.validator.constraints.Length;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 提交借款申请.客户端请求
 * 
 * @author 代码生成器v1.0
 * 
 */
public class SubmitLoanRequest extends MobileMessage {


	private String amount;
	private String idcardno;
	private String phonenumber;
	private String realname;
	private String term;
	private String uses;

	public SubmitLoanRequest() {
		super();
	}	

	/**
	 * @return 借款金额
	 */
	@Length(max = 20,min = 1)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return 借款人身份证号
	 */
	@Length(max = 20,min = 1)
	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	/**
	 * @return 联系方式
	 */
	@Length(max = 20,min = 1)
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return 借款人姓名
	 */
	@Length(max = 20,min = 1)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return 借款期限
	 */
	@Length(max = 20,min = 1)
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return 资金用途
	 */
	@Length(max = 20,min = 1)
	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}
}