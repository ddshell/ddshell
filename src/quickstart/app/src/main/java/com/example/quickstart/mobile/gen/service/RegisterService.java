package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.RegisterRequest;
import com.example.quickstart.mobile.gen.protocol.RegisterResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class RegisterService implements MessageService<RegisterRequest, RegisterResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(RegisterService.class);


	@Override
	public void execute(RegisterRequest request, RegisterResponse response) {
		LOG.debug("It's work.");

	}

}
