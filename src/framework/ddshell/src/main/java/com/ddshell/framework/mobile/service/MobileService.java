package com.ddshell.framework.mobile.service;

import java.io.Serializable;
import java.util.HashSet;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ddshell.framework.mobile.entity.MobileMessage;
import com.ddshell.framework.security.shiro.ShiroMobileSecurityManager;
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
	private ShiroMobileSecurityManager securityManager;

	private ObjectMapper objectMapper = new ObjectMapper();

	public <REQUEST extends MobileMessage, RESPONSE extends MobileMessage> RESPONSE serviceForAuthcForm(
			REQUEST request, AuthcFormService<REQUEST, RESPONSE> service,
			Class<REQUEST> requestType, Class<RESPONSE> responseType) {
		try {
			try {
				String sessionId = securityManager.login(
						service.getLoginName(request),
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

			if (securityManager.isExpired(sessionId)) {
				return createResponse(TIMEOUT, "超时.", responseType);
			}

			if (!securityManager.getSubject(sessionId).isAuthenticated()) {
				return createResponse(NOAUTH, "请登录。", responseType);
			}

			if (securityManager.isConcurrent(sessionId)) {
				securityManager.getSubject(sessionId).logout();
				return createResponse(FAIL, "您已在别处登录，此处已下线.", responseType);
			}

			if ("ENABLED".equalsIgnoreCase(securityStatus)) {
				byte[] key = getHmacSha1Key(sessionId);
				if (key == null) {
					return createResponse(FAIL, "秘钥无效.", responseType);
				}

				if (!Cryptos.isMacValid(request.getMac(), request.getContent(),
						key)) {
					return createResponse(FAIL, "验签失败.", responseType);
				}

				byte[] bytes = null;

				if (request.isEncrypt()) {
					key = getAesKey(sessionId);
					if (key == null) {
						return createResponse(FAIL, "秘钥无效.", responseType);
					}
					bytes = Cryptos.aesDecrypt(request.getContent(), key);
				} else {
					bytes = Base64.decode(request.getContent());
				}

				request = objectMapper.readValue(bytes, requestType);

				if (!isTokenValid(sessionId, request.getToken())) {
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

	public String getHmacSha1Keytext(Serializable sessionId) {
		byte[] key = getHmacSha1Key(sessionId);
		if (key == null) {
			return null;
		}
		return Base64.encodeToString(key);
	}

	public String getAesKeytext(Serializable sessionId) {
		byte[] key = getAesKey(sessionId);
		if (key == null) {
			return null;
		}
		return Base64.encodeToString(key);
	}

	public byte[] getHmacSha1Key(Serializable sessionId) {
		Session session = securityManager.getSubject(sessionId)
				.getSession(true);
		byte[] key = (byte[]) session.getAttribute(HMACSHA1);
		if (key == null) {
			key = Cryptos.generateHmacSha1Key(HMAC_KEYSIZE);
			session.setAttribute(HMACSHA1, key);
		}

		return key;
	}

	public byte[] getAesKey(Serializable sessionId) {
		Session session = securityManager.getSubject(sessionId)
				.getSession(true);
		byte[] key = (byte[]) session.getAttribute(AES);
		if (key == null) {
			key = Cryptos.generateAesKey(AES_KEYSIZE);
			session.setAttribute(AES, key);
		}

		return key;
	}

	@SuppressWarnings("unchecked")
	public boolean isTokenValid(Serializable sessionId, String token) {
		Session session = securityManager.getSubject(sessionId)
				.getSession(true);

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

}
