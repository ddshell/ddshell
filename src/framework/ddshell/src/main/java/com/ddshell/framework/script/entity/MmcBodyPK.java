package com.ddshell.framework.script.entity;

import java.io.Serializable;

public class MmcBodyPK implements Serializable {

	private static final long serialVersionUID = -609979819276992523L;

	private String mmcId;
	private String id;

	public String getMmcId() {
		return mmcId;
	}

	public void setMmcId(String mmcId) {
		this.mmcId = mmcId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return 31 * (31 + mmcId.hashCode()) + id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof MmcBodyPK)) {
			return false;
		}

		MmcBodyPK o = (MmcBodyPK) obj;

		return mmcId.equals(o.mmcId) && id.equals(o.id);
	}

}
