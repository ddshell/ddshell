package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.SubmitLoanRequest;
import com.example.quickstart.mobile.gen.protocol.SubmitLoanResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class SubmitLoanService implements MessageService<SubmitLoanRequest, SubmitLoanResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(SubmitLoanService.class);


	@Override
	public void execute(SubmitLoanRequest request, SubmitLoanResponse response) {
		LOG.debug("It's work.");

	}

}
