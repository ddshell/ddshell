package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.GetLoanRequest;
import com.example.quickstart.mobile.gen.protocol.GetLoanResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class GetLoanService implements MessageService<GetLoanRequest, GetLoanResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(GetLoanService.class);


	@Override
	public void execute(GetLoanRequest request, GetLoanResponse response) {
		LOG.debug("It's work.");

		response.setAmount("10000");
		response.setApplytime("2014/10/14 10:10:10");
		response.setIdcardno("370202197208228012");
		response.setInterestrate("12%");
		response.setPhonenumber("13904719527");
		response.setRealname("李强");
		response.setStatustext("贷款发放");
		response.setTerm("3个月");
	}

}
