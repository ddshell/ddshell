package com.ddshell.framework.security.service;

import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.common.util.GenericCrudService;
import com.ddshell.framework.security.entity.Role;
import com.ddshell.framework.security.repository.RoleRepository;

@Transactional(readOnly = true)
public abstract class RoleService<T extends Role> extends
		GenericCrudService<T, Long> {

	public Role findByName(String name) {
		return ((RoleRepository<T>) getRepository()).findByName(name);
	}

}
