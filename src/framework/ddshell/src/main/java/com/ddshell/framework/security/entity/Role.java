package com.ddshell.framework.security.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Role {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}