package com.ddshell.framework.common.service;

import java.util.Locale;

public interface MailService {

	public void send(String[] to, String mailName, Object context,
			Locale locale, String defaultMailSubject);

	public void send(String[] to, String subject, String templateName,
			Object context);

	public void send(String[] to, String subject, String text);

	public void send(String from, String[] to, String subject,
			String templateName, Object context);

	public void send(String from, String[] to, String subject, String text);

}