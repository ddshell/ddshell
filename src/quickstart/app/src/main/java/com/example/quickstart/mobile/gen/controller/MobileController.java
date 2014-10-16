package com.example.quickstart.mobile.gen.controller;

import com.ddshell.framework.mobile.service.AuthcFormService;
import com.ddshell.framework.mobile.service.MessageService;
import com.ddshell.framework.mobile.service.MobileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.quickstart.mobile.gen.protocol.GetLoanRequest;
import com.example.quickstart.mobile.gen.protocol.GetLoanResponse;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByEmailRequest;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByEmailResponse;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByPhoneRequest;
import com.example.quickstart.mobile.gen.protocol.GetPasswordByPhoneResponse;
import com.example.quickstart.mobile.gen.protocol.GetRegisterVfcodeRequest;
import com.example.quickstart.mobile.gen.protocol.GetRegisterVfcodeResponse;
import com.example.quickstart.mobile.gen.protocol.LoginRequest;
import com.example.quickstart.mobile.gen.protocol.LoginResponse;
import com.example.quickstart.mobile.gen.protocol.RegisterRequest;
import com.example.quickstart.mobile.gen.protocol.RegisterResponse;
import com.example.quickstart.mobile.gen.protocol.SubmitLoanRequest;
import com.example.quickstart.mobile.gen.protocol.SubmitLoanResponse;
import com.example.quickstart.mobile.gen.service.GetLoanService;
import com.example.quickstart.mobile.gen.service.GetPasswordByEmailService;
import com.example.quickstart.mobile.gen.service.GetPasswordByPhoneService;
import com.example.quickstart.mobile.gen.service.GetRegisterVfcodeService;
import com.example.quickstart.mobile.gen.service.LoginService;
import com.example.quickstart.mobile.gen.service.RegisterService;
import com.example.quickstart.mobile.gen.service.SubmitLoanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/mobile")
@Controller
public class MobileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(MobileController.class);

	@Qualifier("getLoanService")
	@Autowired(required = false)
	MessageService<GetLoanRequest, GetLoanResponse> getLoanService;
	@Qualifier("getPasswordByEmailService")
	@Autowired(required = false)
	MessageService<GetPasswordByEmailRequest, GetPasswordByEmailResponse> getPasswordByEmailService;
	@Qualifier("getPasswordByPhoneService")
	@Autowired(required = false)
	MessageService<GetPasswordByPhoneRequest, GetPasswordByPhoneResponse> getPasswordByPhoneService;
	@Qualifier("getRegisterVfcodeService")
	@Autowired(required = false)
	MessageService<GetRegisterVfcodeRequest, GetRegisterVfcodeResponse> getRegisterVfcodeService;
	@Qualifier("loginService")
	@Autowired(required = false)
	AuthcFormService<LoginRequest, LoginResponse> loginService;
	@Qualifier("registerService")
	@Autowired(required = false)
	MessageService<RegisterRequest, RegisterResponse> registerService;
	@Qualifier("submitLoanService")
	@Autowired(required = false)
	MessageService<SubmitLoanRequest, SubmitLoanResponse> submitLoanService;

	@Autowired
	MobileService mobileService;

	ObjectMapper objectMapper = new ObjectMapper();


	@RequestMapping(value = "/getLoan", method = RequestMethod.POST)
	@ResponseBody
	public GetLoanResponse getLoan(@RequestBody GetLoanRequest request) {
		debug(request);
		GetLoanResponse response = getGetLoanResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/getPasswordByEmail", method = RequestMethod.POST)
	@ResponseBody
	public GetPasswordByEmailResponse getPasswordByEmail(@RequestBody GetPasswordByEmailRequest request) {
		debug(request);
		GetPasswordByEmailResponse response = getGetPasswordByEmailResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/getPasswordByPhone", method = RequestMethod.POST)
	@ResponseBody
	public GetPasswordByPhoneResponse getPasswordByPhone(@RequestBody GetPasswordByPhoneRequest request) {
		debug(request);
		GetPasswordByPhoneResponse response = getGetPasswordByPhoneResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/getRegisterVfcode", method = RequestMethod.POST)
	@ResponseBody
	public GetRegisterVfcodeResponse getRegisterVfcode(@RequestBody GetRegisterVfcodeRequest request) {
		debug(request);
		GetRegisterVfcodeResponse response = getGetRegisterVfcodeResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@RequestBody LoginRequest request) {
		debug(request);
		LoginResponse response = getLoginResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		debug(request);
		RegisterResponse response = getRegisterResponse(request);
		debug(response);

		return response;
	}

	@RequestMapping(value = "/submitLoan", method = RequestMethod.POST)
	@ResponseBody
	public SubmitLoanResponse submitLoan(@RequestBody SubmitLoanRequest request) {
		debug(request);
		SubmitLoanResponse response = getSubmitLoanResponse(request);
		debug(response);

		return response;
	}


	private GetLoanResponse getGetLoanResponse(GetLoanRequest request) {

		if (getLoanService == null) {
			GetLoanResponse response = new GetLoanResponse();
			new GetLoanService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAuthc(request, getLoanService,
				GetLoanRequest.class, GetLoanResponse.class);
	}

	private GetPasswordByEmailResponse getGetPasswordByEmailResponse(GetPasswordByEmailRequest request) {

		if (getPasswordByEmailService == null) {
			GetPasswordByEmailResponse response = new GetPasswordByEmailResponse();
			new GetPasswordByEmailService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAnon(request, getPasswordByEmailService,
				GetPasswordByEmailRequest.class, GetPasswordByEmailResponse.class);
	}

	private GetPasswordByPhoneResponse getGetPasswordByPhoneResponse(GetPasswordByPhoneRequest request) {

		if (getPasswordByPhoneService == null) {
			GetPasswordByPhoneResponse response = new GetPasswordByPhoneResponse();
			new GetPasswordByPhoneService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAnon(request, getPasswordByPhoneService,
				GetPasswordByPhoneRequest.class, GetPasswordByPhoneResponse.class);
	}

	private GetRegisterVfcodeResponse getGetRegisterVfcodeResponse(GetRegisterVfcodeRequest request) {

		if (getRegisterVfcodeService == null) {
			GetRegisterVfcodeResponse response = new GetRegisterVfcodeResponse();
			new GetRegisterVfcodeService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAnon(request, getRegisterVfcodeService,
				GetRegisterVfcodeRequest.class, GetRegisterVfcodeResponse.class);
	}

	private LoginResponse getLoginResponse(LoginRequest request) {

		if (loginService == null) {
			LoginResponse response = new LoginResponse();
			new LoginService().onSuccess(request, response);
			return response;
		}

		return mobileService.serviceForAuthcForm(request, loginService,
				LoginRequest.class, LoginResponse.class);
	}

	private RegisterResponse getRegisterResponse(RegisterRequest request) {

		if (registerService == null) {
			RegisterResponse response = new RegisterResponse();
			new RegisterService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAnon(request, registerService,
				RegisterRequest.class, RegisterResponse.class);
	}

	private SubmitLoanResponse getSubmitLoanResponse(SubmitLoanRequest request) {

		if (submitLoanService == null) {
			SubmitLoanResponse response = new SubmitLoanResponse();
			new SubmitLoanService().execute(request, response);
			return response;
		}

		return mobileService.serviceForAuthc(request, submitLoanService,
				SubmitLoanRequest.class, SubmitLoanResponse.class);
	}

	private void debug(Object obj) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(objectMapper.writeValueAsString(obj));
			}
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
		}
	}

}
