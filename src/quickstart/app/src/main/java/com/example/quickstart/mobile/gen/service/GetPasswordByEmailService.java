package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByEmailRequest;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByEmailResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class GetPasswordByEmailService implements MessageService<GetPasswordByEmailRequest, GetPasswordByEmailResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(GetPasswordByEmailService.class);


	@Override
	public void execute(GetPasswordByEmailRequest request, GetPasswordByEmailResponse response) {
		LOG.debug("It's work.");

	}

}
