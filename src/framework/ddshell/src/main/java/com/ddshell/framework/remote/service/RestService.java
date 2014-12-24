package com.ddshell.framework.remote.service;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ddshell.framework.remote.entity.RemoteMessage;
import com.ddshell.framework.security.util.Cryptos;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestService {

	public static final String SESSION_NONE = "SESSION_NONE";

	private static final Logger LOG = LoggerFactory
			.getLogger(RestService.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SecretKeyService secretKeyService;

	private ObjectMapper objectMapper = new ObjectMapper();

	public <T> T post(String sessionId, String url, Object request,
			Class<T> responseType) {
		RemoteMessage message = post(sessionId, url, request);

		try {
			return decode(message, responseType);
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
			throw new RuntimeException(t.getMessage(), t);
		}
	}

	public Object postX(String sessionId, String url, Object request,
			Class<?> parametrizedResponseType, Class<?>... parameterClasses) {
		RemoteMessage message = post(sessionId, url, request);

		try {
			return decode(message, parametrizedResponseType, parameterClasses);
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
			throw new RuntimeException(t.getMessage(), t);
		}
	}

	public <T> RemoteMessage service(RemoteMessage message,
			MessageService service, Class<T> requestType) {

		onFail(message);

		String sessionId = message.getSessionId();
		try {
			return encode(sessionId,
					service.execute(decode(message, requestType)));
		} catch (Throwable t) {
			return fail(sessionId, t);
		}
	}

	public RemoteMessage serviceX(RemoteMessage message,
			MessageService service, Class<?> parametrizedRequestType,
			Class<?>... parameterClasses) {

		onFail(message);

		String sessionId = message.getSessionId();
		try {
			return encode(sessionId, service.execute(decode(message,
					parametrizedRequestType, parameterClasses)));
		} catch (Throwable t) {
			return fail(sessionId, t);
		}
	}

	protected boolean isEncrypt() {
		return false;
	}

	private RemoteMessage post(String sessionId, String url, Object request) {
		LOG.info("POST {}", url);

		RemoteMessage message = null;

		try {
			message = restTemplate.postForObject(url,
					encode(sessionId, request), RemoteMessage.class);
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
			throw new RuntimeException(t.getMessage(), t);
		}

		onFail(message);

		return message;
	}

	private RemoteMessage encode(String sessionId, Object value)
			throws Exception {
		byte[] content = objectMapper.writeValueAsBytes(value);

		if (isEncrypt()) {
			content = Cryptos.aesEncrypt(content,
					secretKeyService.getAesKey(sessionId));
		}

		byte[] mac = Cryptos.hmacSha1(content,
				secretKeyService.getHmacKey(sessionId));

		return new RemoteMessage(sessionId, Base64.encodeToString(content),
				Base64.encodeToString(mac));
	}

	private <T> T decode(RemoteMessage message, Class<T> valueType)
			throws Exception {
		return objectMapper.readValue(decode(message), valueType);
	}

	private Object decode(RemoteMessage message, Class<?> parametrized,
			Class<?>... parameterClasses) throws Exception {
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(parametrized, parameterClasses);
		return objectMapper.readValue(decode(message), javaType);
	}

	private byte[] decode(RemoteMessage message) {
		byte[] hmacKey = secretKeyService.getHmacKey(message.getSessionId());
		if (!Cryptos
				.isMacValid(message.getMac(), message.getContent(), hmacKey)) {
			throw new RuntimeException("mac invalid.");
		}

		byte[] content = Base64.decode(message.getContent());

		if (isEncrypt()) {
			byte[] aesKey = secretKeyService.getAesKey(message.getSessionId());
			content = Cryptos.aesDecrypt(content, aesKey);
		}

		return content;
	}

	private void onFail(RemoteMessage message) {
		if (message == null) {
			return;
		}

		if (RemoteMessage.FAIL.equalsIgnoreCase(message.getStatusCode())) {
			RuntimeException t = new RuntimeException(
					message.getStatusMessage(), new Exception(
							message.getStatusCode()));
			LOG.error(t.getMessage(), t);
			throw t;
		}

	}

	private RemoteMessage fail(String sessionId, Throwable t) {
		LOG.error(t.getMessage(), t);
		return new RemoteMessage(RemoteMessage.FAIL, t.getMessage(), sessionId,
				null, null);
	}

}
