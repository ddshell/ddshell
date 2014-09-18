package com.example.quickstart.security.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ddshell.framework.security.entity.Menu;

@MappedSuperclass
public abstract class MenuEntity extends Menu {

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
