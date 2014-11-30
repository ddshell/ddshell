package com.ddshell.framework.mobile.service;

import java.io.Serializable;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ddshell.framework.mobile.entity.MobileMessage;
import com.ddshell.framework.security.util.Cryptos;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MobileService {

	public static final String SUCCESS = "0";
	public static final String FAIL = "-1";
	public static final String TIMEOUT = "-100";
	public static final String NOAUTH = "-200";

	private static String getSessionKey(String name) {
		return MobileService.class.getName() + "." + name;
	}

	private static final String CONCURRENT = getSessionKey("concurrent");

	private static final int AES_KEYSIZE = 256;
	private static final String AES = getSessionKey("aes");

	private static final int HMAC_KEYSIZE = 256;
	private static final String HMACSHA1 = getSessionKey("hmacsha1");

	private static final String TOKENCACHE = getSessionKey("tokencache");

	private static final Logger LOG = LoggerFactory
			.getLogger(MobileService.class);

	@Value("${mobile.security.status:DISABLED}")
	private String securityStatus;

	@Autowired
	@Qualifier("defaultSecurityManager")
	private SessionsSecurityManager securityManager;

	private ObjectMapper objectMapper = new ObjectMapper();

	public <REQUEST extends MobileMessage, RESPONSE extends MobileMessage> RESPONSE serviceForAuthcForm(
			REQUEST request, AuthcFormService<REQUEST, RESPONSE> service,
			Class<REQUEST> requestType, Class<RESPONSE> responseType) {
		try {
			try {
				String sessionId = login(service.getLoginName(request),
						service.getPassword(request)).toString();
				request.setSessionId(sessionId);

				RESPONSE response = createResponse(responseType);
				service.onSuccess(request, response);
				response.setSessionId(sessionId);
				return response;
			} catch (UnknownAccountException e) {
				return createResponse(FAIL, "未知用户.", responseType);
			} catch (DisabledAccountException e) {
				return createResponse(FAIL, "用户已禁用.", responseType);
			} catch (Exception e) {
				RESPONSE response = createResponse(FAIL, e.getMessage(),
						responseType);
				service.onException(request, response, e);
				return response;
			}
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
			return createResponse(FAIL, e.getMessage(), responseType);
		}
	}

	public <REQUEST extends MobileMessage, RESPONSE extends MobileMessage> RESPONSE serviceForAuthc(
			REQUEST request, MessageService<REQUEST, RESPONSE> service,
			Class<REQUEST> requestType, Class<RESPONSE> responseType) {
		try {
			String sessionId = request.getSessionId();

			if (isExpired(sessionId)) {
				return createResponse(TIMEOUT, "超时.", responseType);
			}

			if (!SecurityUtils.getSubject().isAuthenticated()) {
				return createResponse(NOAUTH, "请登录。", responseType);
			}

			if (isConcurrent()) {
				SecurityUtils.getSubject().logout();
				return createResponse(FAIL, "您已在别处登录，此处已下线.", responseType);
			}

			if ("ENABLED".equalsIgnoreCase(securityStatus)) {
				byte[] key = getHmacSha1Key();
				if (key == null) {
					return createResponse(FAIL, "秘钥无效.", responseType);
				}

				if (!Cryptos.isMacValid(request.getMac(), request.getContent(),
						key)) {
					return createResponse(FAIL, "验签失败.", responseType);
				}

				byte[] bytes = null;

				if (request.isEncrypt()) {
					key = getAesKey();
					if (key == null) {
						return createResponse(FAIL, "秘钥无效.", responseType);
					}
					bytes = Cryptos.aesDecrypt(request.getContent(), key);
				} else {
					bytes = Base64.decode(request.getContent());
				}

				request = objectMapper.readValue(bytes, requestType);

				if (!isTokenValid(request.getToken())) {
					return createResponse(FAIL, "重复提交.", responseType);
				}
			}

			RESPONSE response = createResponse(responseType);
			service.execute(request, response);
			response.setSessionId(sessionId);
			return response;
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
			return createResponse(FAIL, e.getMessage(), responseType);
		}
	}

	public <REQUEST extends MobileMessage, RESPONSE extends MobileMessage> RESPONSE serviceForAnon(
			REQUEST request, MessageService<REQUEST, RESPONSE> service,
			Class<REQUEST> requestType, Class<RESPONSE> responseType) {
		try {
			RESPONSE response = createResponse(responseType);
			service.execute(request, response);
			return response;
		} catch (Throwable e) {
			LOG.error(e.getMessage(), e);
			return createResponse(FAIL, e.getMessage(), responseType);
		}
	}

	public void bindSubject(String sessionId) {
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = new Subject.Builder(securityManager).sessionId(
				sessionId).buildSubject();
		ThreadContext.bind(subject);
	}

	public String getHmacSha1Keytext(Serializable sessionId) {
		byte[] key = getHmacSha1Key();
		if (key == null) {
			return null;
		}
		return Base64.encodeToString(key);
	}

	public String getAesKeytext(Serializable sessionId) {
		byte[] key = getAesKey();
		if (key == null) {
			return null;
		}
		return Base64.encodeToString(key);
	}

	private byte[] getHmacSha1Key() {
		Session session = SecurityUtils.getSubject().getSession(true);
		byte[] key = (byte[]) session.getAttribute(HMACSHA1);
		if (key == null) {
			key = Cryptos.generateHmacSha1Key(HMAC_KEYSIZE);
			session.setAttribute(HMACSHA1, key);
		}

		return key;
	}

	private byte[] getAesKey() {
		Session session = SecurityUtils.getSubject().getSession(true);
		byte[] key = (byte[]) session.getAttribute(AES);
		if (key == null) {
			key = Cryptos.generateAesKey(AES_KEYSIZE);
			session.setAttribute(AES, key);
		}

		return key;
	}

	@SuppressWarnings("unchecked")
	private boolean isTokenValid(String token) {
		Session session = SecurityUtils.getSubject().getSession(true);

		HashSet<String> cache = (HashSet<String>) session
				.getAttribute(TOKENCACHE);
		if (cache == null) {
			cache = new HashSet<String>();
			session.setAttribute(TOKENCACHE, cache);
		}
		if (cache.contains(token)) {
			return false;
		}
		cache.add(token);

		return true;
	}

	private boolean isExpired(Serializable sessionId) {
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

	private boolean isConcurrent() {
		Session session = SecurityUtils.getSubject().getSession();
		if (session == null) {
			return false;
		}

		Object concurrent = session.getAttribute(CONCURRENT);
		if (concurrent == null || !(concurrent instanceof Boolean)) {
			return false;
		}

		return (Boolean) concurrent;
	}

	private Serializable login(String loginName, String password) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		UsernamePasswordToken token = new UsernamePasswordToken(loginName,
				password, request.getRemoteAddr());

		Subject subject = SecurityUtils.getSubject();
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

	private void processConcurrentSessions(Serializable currentSessionId) {
		try {
			String currentUser = getLoginName();
			if (currentUser == null) {
				return;
			}

			for (Session session : getSessionDAO().getActiveSessions()) {
				Serializable sessionId = session.getId();
				if (sessionId.equals(currentSessionId)) {
					continue;
				}

				String user = getLoginName();
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

	private String getLoginName() {
		Subject subject = SecurityUtils.getSubject();

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

	private <T extends MobileMessage> T createResponse(String statusCode,
			String statusMessage, Class<T> responseType) {
		T response = createResponse(responseType);
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}

	private <T extends MobileMessage> T createResponse(Class<T> responseType) {
		try {
			T response = responseType.newInstance();
			response.setStatusCode(SUCCESS);
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
