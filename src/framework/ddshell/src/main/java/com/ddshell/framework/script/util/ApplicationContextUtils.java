package com.ddshell.framework.script.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtils {

	private static ClassPathXmlApplicationContext ctx;

	public static <T> T getBean(Class<T> requiredType) {
		return getContext().getBean(requiredType);
	}

	private static ClassPathXmlApplicationContext getContext() {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(
					"classpath*:/com/ddshell/spring/*.xml",
					"classpath:/spring/*.xml");
		}

		return ctx;
	}

}
