package com.example.quickstart.mobile.core.service;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddshell.framework.mobile.service.AuthcFormService;
import com.example.quickstart.mobile.gen.protocol.LoginRequest;
import com.example.quickstart.mobile.gen.protocol.LoginResponse;
import com.example.quickstart.security.entity.User;
import com.example.quickstart.security.service.UserService;

@Component
public class LoginService implements
		AuthcFormService<LoginRequest, LoginResponse> {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginService.class);

	@Autowired
	private UserService userService;

	@Override
	public String getLoginName(LoginRequest request) {
		return request.getIdcardno();
	}

	@Override
	public String getPassword(LoginRequest request) {
		return request.getPassword();
	}

	@Override
	public void onException(LoginRequest request, LoginResponse response,
			Exception e) {
		LOG.error(e.getMessage(), e);

		if (e instanceof IncorrectCredentialsException) {
			response.setStatusMessage("用户名密码不匹配.");
		}
	}

	@Override
	public void onSuccess(LoginRequest request, LoginResponse response) {
		LOG.debug("It's work.");

		User loginUser = userService.getLoginUser();

		response.setIdcardno(loginUser.getIdcardno());
		response.setPhonenumber(loginUser.getPhonenumber());
		response.setRealname(loginUser.getRealname());
	}
}
