package com.ddshell.framework.mobile.service;

public interface MessageService<REQUEST, RESPONSE> {

	public void execute(REQUEST request, RESPONSE response);

}
