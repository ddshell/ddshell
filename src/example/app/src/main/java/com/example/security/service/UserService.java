package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;

@Transactional(readOnly = true)
@DependsOn("userRepository")
@Component
public class UserService extends
		com.ddshell.framework.security.service.UserService<User> {

	@Autowired
	private UserRepository userRep;

	@Override
	protected JpaRepository<User, Long> getRepository() {
		return userRep;
	}

}
