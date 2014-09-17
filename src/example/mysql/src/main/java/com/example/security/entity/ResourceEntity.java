package com.example.security.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.ddshell.framework.security.entity.Resource;

@MappedSuperclass
public abstract class ResourceEntity extends Resource {

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
