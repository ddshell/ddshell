package com.ddshell.framework.security.shiro.service;

import com.ddshell.framework.security.shiro.entity.ShiroUser;

public interface ShiroUserService {

	public ShiroUser findByLoginName(String loginName);

	public byte[] getSaltBytes(ShiroUser user);

}
