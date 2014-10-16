package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.MessageService;
import com.example.quickstart.mobile.gen.protocol.GetRegisterVfcodeRequest;
import com.example.quickstart.mobile.gen.protocol.GetRegisterVfcodeResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class GetRegisterVfcodeService implements MessageService<GetRegisterVfcodeRequest, GetRegisterVfcodeResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(GetRegisterVfcodeService.class);


	@Override
	public void execute(GetRegisterVfcodeRequest request, GetRegisterVfcodeResponse response) {
		LOG.debug("It's work.");

	}

}
