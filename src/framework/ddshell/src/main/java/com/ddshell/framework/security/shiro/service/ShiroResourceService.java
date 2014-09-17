package com.ddshell.framework.security.shiro.service;

import java.util.List;

import com.ddshell.framework.security.shiro.entity.ShiroResource;

public interface ShiroResourceService {

	public List<? extends ShiroResource> findAll();

}
