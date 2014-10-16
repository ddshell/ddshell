package com.ddshell.framework.security.service;

import java.io.Serializable;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ddshell.framework.common.util.GenericCrudService;
import com.ddshell.framework.common.util.GlobalVars;
import com.ddshell.framework.security.entity.User;
import com.ddshell.framework.security.repository.UserRepository;
import com.ddshell.framework.security.shiro.ShiroMobileSecurityManager;
import com.ddshell.framework.security.shiro.entity.ShiroUser;
import com.ddshell.framework.security.shiro.service.ShiroUserService;

@Transactional(readOnly = true)
public abstract class UserService<T extends User> extends
		GenericCrudService<T, Long> implements ShiroUserService {

	private static final String LOGIN_USER = UserService.class.getName()
			+ ".loginUser";

	@Autowired
	private GlobalVars globalVars;
	@Autowired
	protected ShiroMobileSecurityManager shiroMobileSecurityManager;

	public T findByLoginName(String loginName) {
		return ((UserRepository<T>) getRepository()).findByLoginName(loginName);
	}

	@Override
	public byte[] getSaltBytes(ShiroUser user) {
		String salt = user.getSalt();
		if (salt == null) {
			return null;
		}

		try {
			return Hex.decodeHex(salt.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	@Override
	public <S extends T> S save(S user) {
		String plainPassword = user.getPlainPassword();
		if (!StringUtils.isEmpty(plainPassword)) {
			user.setPassword(encodeUserPassword(plainPassword, user));
		}
		return super.save(user);
	}

	@Transactional
	public boolean changeLoginUserPassword(String plainOldPassword,
			String plainNewPassword) {
		return changeLoginUserPassword(null, plainOldPassword, plainNewPassword);
	}

	@Transactional
	public boolean changeLoginUserPassword(Serializable sessionId,
			String plainOldPassword, String plainNewPassword) {
		if (StringUtils.isEmpty(plainOldPassword)
				|| StringUtils.isEmpty(plainNewPassword)) {
			return false;
		}

		T loginUser = getLoginUser(sessionId);

		if (!encodeUserPassword(plainOldPassword, loginUser).equalsIgnoreCase(
				loginUser.getPassword())) {
			return false;
		}

		T user = findOne(loginUser.getId());
		user.setPlainPassword(plainNewPassword);
		user = save(user);

		loginUser.setPassword(user.getPassword());

		return true;
	}

	public T getLoginUser() {
		return getLoginUser(SecurityUtils.getSubject());
	}

	public T getLoginUser(Serializable sessionId) {
		return getLoginUser(shiroMobileSecurityManager.getSubject(sessionId));
	}

	public void setLoginUser(T loginUser) {
		setLoginUser(SecurityUtils.getSubject(), loginUser);
	}

	public void setLoginUser(Serializable sessionId, T loginUser) {
		setLoginUser(shiroMobileSecurityManager.getSubject(sessionId),
				loginUser);
	}

	private T getLoginUser(Subject subject) {
		if (!subject.isAuthenticated()) {
			return null;
		}

		Session session = subject.getSession(true);
		if (session == null) {
			return null;
		}

		@SuppressWarnings("unchecked")
		T loginUser = (T) session.getAttribute(LOGIN_USER);
		if (loginUser == null) {
			loginUser = findByLoginName((String) subject.getPrincipal());
			session.setAttribute(LOGIN_USER, loginUser);
		}

		return loginUser;
	}

	private void setLoginUser(Subject subject, T loginUser) {
		Session session = subject.getSession(true);
		if (session == null) {
			return;
		}

		session.setAttribute(LOGIN_USER, loginUser);
	}

	private String encodeUserPassword(String plainPassword, T user) {
		SimpleHash hash = new SimpleHash(globalVars.hashAlgorithmName,
				plainPassword, getSaltBytes(user), globalVars.hashIterations);
		return hash.toString();
	}

}
