package com.ddshell.framework.remote.service;

public interface SecretKeyService {

	public byte[] getHmacKey(String sessionId);

	public byte[] getAesKey(String sessionId);

}
