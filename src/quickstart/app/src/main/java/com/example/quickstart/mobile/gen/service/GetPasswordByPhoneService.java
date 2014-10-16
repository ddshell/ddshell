package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByPhoneRequest;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByPhoneResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class GetPasswordByPhoneService implements MessageService<GetPasswordByPhoneRequest, GetPasswordByPhoneResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(GetPasswordByPhoneService.class);


	@Override
	public void execute(GetPasswordByPhoneRequest request, GetPasswordByPhoneResponse response) {
		LOG.debug("It's work.");

	}

}
