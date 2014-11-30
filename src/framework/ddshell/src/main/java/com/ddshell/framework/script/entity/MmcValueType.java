package com.ddshell.framework.script.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mmc_valuetype")
public class MmcValueType {

	@Id
	private String dbType;
	private String javaType;
	private String objcType;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getObjcType() {
		return objcType;
	}

	public void setObjcType(String objcType) {
		this.objcType = objcType;
	}
}
