package com.example.quickstart.mobile.gen.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * 提交借款申请.服务端响应
 * 
 * @author 代码生成器v1.0
 * 
 */
@JsonInclude(Include.NON_NULL)
public class SubmitLoanResponse extends MobileMessage {



	public SubmitLoanResponse() {
		super();
	}

	public SubmitLoanResponse(String statusCode, String statusMessage) {
		this.setStatusCode(statusCode);
		this.setStatusMessage(statusMessage);
	}

}