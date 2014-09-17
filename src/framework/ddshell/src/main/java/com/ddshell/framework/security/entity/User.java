package com.ddshell.framework.security.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.ddshell.framework.security.shiro.entity.ShiroUser;

@MappedSuperclass
public abstract class User implements ShiroUser {

	protected static final String ENABLED = "ENABLED";
	protected static final String DISABLED = "DISABLED";

	private String loginName;
	private String password;
	private String salt;
	private String status;
	private String defaultLocale;
	private Date createTime;
	private Date updateTime;

	@Transient
	private String plainPassword;
	@Transient
	private boolean disabled;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	protected String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	public String getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
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

	public boolean isDisabled() {
		// return disabled;
		return DISABLED.equals(status);
	}

	public void setDisabled(boolean disabled) {
		// this.disabled = disabled;
		if (disabled) {
			status = DISABLED;
		} else {
			status = ENABLED;
		}
	}

	public abstract Long getId();

}