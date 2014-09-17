package com.ddshell.framework.security.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ddshell.framework.security.shiro.entity.ShiroResource;

@MappedSuperclass
public abstract class Resource implements ShiroResource {

	private String text;
	private String pattern;
	private String permission;
	protected Date createTime;
	protected Date updateTime;

	public Resource() {
		super();
	}

	public Resource(String pattern, String permission) {
		super();
		this.pattern = pattern;
		this.permission = permission;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
		this.updateTime = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
	}
}