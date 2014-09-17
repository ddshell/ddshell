package com.ddshell.framework.security.shiro.entity;

import java.util.Collection;

public interface ShiroUser {

	public boolean isDisabled();

	public String getLoginName();

	public String getPassword();

	public Collection<String> getRoleNames();

	public Collection<String> getPermissionNames();

	public String getSalt();

}
