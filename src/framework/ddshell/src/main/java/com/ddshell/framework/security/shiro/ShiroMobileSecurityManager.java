package com.ddshell.framework.security.shiro;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class ShiroMobileSecurityManager {

	private static final Logger LOG = LoggerFactory
			.getLogger(ShiroMobileSecurityManager.class);

	private static final String CONCURRENT = ShiroMobileSecurityManager.class
			.getName() + "." + "concurrent";

	@Autowired
	@Qualifier("mobileSecurityManager")
	private SessionsSecurityManager securityManager;

	public boolean isExpired(Serializable sessionId) {
		if (sessionId == null) {
			return false;
		}

		try {
			return isExpired(getSessionDAO().readSession(sessionId));
		} catch (Throwable t) {
			LOG.debug(t.getMessage(), t);
			return false;
		}
	}

	public boolean isConcurrent(Serializable sessionId) {
		if (sessionId == null) {
			return false;
		}

		Session session = getSubject(sessionId).getSession();
		if (session == null) {
			return false;
		}

		Object concurrent = session.getAttribute(CONCURRENT);
		if (concurrent == null || !(concurrent instanceof Boolean)) {
			return false;
		}

		return (Boolean) concurrent;
	}

	public Serializable login(String loginName, String password) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		UsernamePasswordToken token = new UsernamePasswordToken(loginName,
				password, request.getRemoteAddr());

		Subject subject = getSubject(null);
		try {
			subject.login(token);
		} catch (InvalidSessionException e) {
			subject.logout();
			subject.login(token);
		}

		Session session = subject.getSession();
		Serializable sessionId = session.getId();

		LOG.debug("Session with id [{}] startTimestamp:{}", sessionId, session
				.getStartTimestamp().getTime());

		try {
			Thread.sleep(100);
		} catch (Throwable ignored) {
		}

		session.touch();

		LOG.debug("Session with id [{}] lastAccessTime:{}", sessionId, session
				.getLastAccessTime().getTime());

		processConcurrentSessions(sessionId);

		return sessionId;
	}

	public Subject getSubject(Serializable sessionId) {
		Subject subject = getCachedSubject(sessionId);

		if (subject == null) {
			SecurityUtils.setSecurityManager(securityManager);
			subject = new Subject.Builder(securityManager).sessionId(sessionId)
					.buildSubject();
			ThreadContext.bind(subject);
		}

		return subject;
	}

	private Subject getCachedSubject(Serializable sessionId) {
		if (sessionId == null) {
			return null;
		}

		Subject subject = ThreadContext.getSubject();

		if (subject == null) {
			return null;
		}

		Session session = subject.getSession();
		if (session == null) {
			return null;
		}

		if (!session.getId().equals(sessionId)) {
			return null;
		}

		return subject;
	}

	private void processConcurrentSessions(Serializable currentSessionId) {
		try {
			String currentUser = getLoginName(currentSessionId);
			if (currentUser == null) {
				return;
			}

			for (Session session : getSessionDAO().getActiveSessions()) {
				Serializable sessionId = session.getId();
				if (sessionId.equals(currentSessionId)) {
					continue;
				}

				String user = getLoginName(sessionId);
				if (user == null) {
					continue;
				}

				if (user.equals(currentUser)) {
					if (!isExpired(session)) {
						session.setAttribute(CONCURRENT, true);
					}
				}
			}
		} catch (Throwable t) {
			LOG.debug(t.getMessage(), t);
		}
	}

	private String getLoginName(Serializable sessionId) {
		Subject subject = getSubject(sessionId);

		if (!subject.isAuthenticated()) {
			return null;
		}

		PrincipalCollection principals = subject.getPrincipals();
		if (principals != null && !principals.isEmpty()) {
			return (String) principals.getPrimaryPrincipal();
		}

		return null;
	}

	private SessionDAO getSessionDAO() {
		DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager
				.getSessionManager();
		return sessionManager.getSessionDAO();
	}

	private boolean isExpired(Session session) {
		if (!(session instanceof ValidatingSession)) {
			session.touch();
			return false;
		}

		try {
			((ValidatingSession) session).validate();
		} catch (ExpiredSessionException e) {
			return true;
		} catch (InvalidSessionException e) {
			return true;
		}

		session.touch();

		return false;
	}

}
