package com.ddshell.framework.mobile.service;

public interface AuthcFormService<REQUEST, RESPONSE> {

	public String getLoginName(REQUEST request);

	public String getPassword(REQUEST request);

	public void onException(REQUEST request, RESPONSE response, Exception e);

	public void onSuccess(REQUEST request, RESPONSE response);

}
