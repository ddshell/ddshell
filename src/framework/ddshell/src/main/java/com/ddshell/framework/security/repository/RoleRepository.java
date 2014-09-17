package com.ddshell.framework.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddshell.framework.security.entity.Role;

public interface RoleRepository<T extends Role> extends JpaRepository<T, Long> {

	public T findByName(String name);

}
