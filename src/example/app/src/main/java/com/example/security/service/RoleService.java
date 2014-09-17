package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.entity.Role;
import com.example.security.repository.RoleRepository;

@Transactional(readOnly = true)
@Component
public class RoleService extends
		com.ddshell.framework.security.service.RoleService<Role> {

	@Autowired
	private RoleRepository roleRep;

	@Override
	protected JpaRepository<Role, Long> getRepository() {
		return roleRep;
	}

}
