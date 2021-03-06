package com.ddshell.framework.common.service.impl;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ddshell.framework.common.service.MailService;
import com.ddshell.framework.common.util.FreemarkerUtils;
import com.ddshell.framework.common.util.GlobalVars;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class MimeMailServiceImpl implements MailService {

	private static Logger LOG = LoggerFactory
			.getLogger(MimeMailServiceImpl.class);

	@Autowired
	private GlobalVars globalVars;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration freemarkerConfiguration;
	@Autowired
	private MessageSource messageSource;

	@Override
	public void send(String[] to, String mailName, Object context,
			Locale locale, String defaultMailSubject) {
		send(to, getMailSubject(mailName, locale, defaultMailSubject),
				getMailTemplateName(mailName, locale), context);
	}

	@Override
	public void send(String[] to, String subject, String templateName,
			Object context) {
		send(globalVars.mailFrom, to, subject, templateName, context);
	}

	@Override
	public void send(String[] to, String subject, String text) {
		send(globalVars.mailFrom, to, subject, text);
	}

	@Override
	@Async
	public void send(String from, String[] to, String subject,
			String templateName, Object context) {
		try {
			Template template = freemarkerConfiguration
					.getTemplate(templateName);
			send(from, to, subject,
					FreemarkerUtils.renderTemplate(template, context));
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	@Async
	public void send(String from, String[] to, String subject, String text) {
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true,
					globalVars.mailEncoding);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
			return;
		}

		try {
			mailSender.send(msg);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private String getMailSubject(String mailName, Locale locale,
			String defaultMailSubject) {
		try {
			return messageSource.getMessage("mail.subject." + mailName, null,
					locale);
		} catch (Exception e) {
			return defaultMailSubject;
		}
	}

	private String getMailTemplateName(String mailName, Locale locale) {
		StringBuffer buf = new StringBuffer();

		buf.append("mail/").append(mailName);
		if (locale != null) {
			buf.append("_").append(locale);
		}
		buf.append(".ftl");

		return buf.toString();
	}

}
