package com.example.quickstart.mobile.gen.service;


import com.ddshell.framework.mobile.service.AuthcFormService;
import com.example.quickstart.mobile.gen.protocol.LoginRequest;
import com.example.quickstart.mobile.gen.protocol.LoginResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class LoginService implements AuthcFormService<LoginRequest, LoginResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginService.class);

	@Override
	public String getLoginName(LoginRequest request) {
 		return null;
	}

	@Override
	public String getPassword(LoginRequest request) {
		return null;
	}

	@Override
	public void onException(LoginRequest request, LoginResponse response, Exception e) {
		LOG.error(e.getMessage(), e);
	}

	@Override
	public void onSuccess(LoginRequest request, LoginResponse response) {
		LOG.debug("It's work.");

		response.setIdcardno("370202197208228012");
		response.setPhonenumber("13904719527");
		response.setRealname("李强");
	}
}
