package com.example.security.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ddshell.framework.security.entity.User;

@MappedSuperclass
public abstract class UserEntity extends User {

	@GeneratedValue
	@Id
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
