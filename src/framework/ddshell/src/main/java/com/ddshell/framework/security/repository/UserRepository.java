package com.ddshell.framework.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddshell.framework.security.entity.User;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {

	public T findByLoginName(String loginName);

}
