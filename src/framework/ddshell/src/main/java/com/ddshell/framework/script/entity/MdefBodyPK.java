package com.ddshell.framework.script.entity;

import java.io.Serializable;

public class MdefBodyPK implements Serializable {

	private static final long serialVersionUID = -609979819276992523L;

	private String mdefId;
	private String id;

	public String getMdefId() {
		return mdefId;
	}

	public void setMdefId(String mdefId) {
		this.mdefId = mdefId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return 31 * (31 + mdefId.hashCode()) + id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof MdefBodyPK)) {
			return false;
		}

		MdefBodyPK o = (MdefBodyPK) obj;

		return mdefId.equals(o.mdefId) && id.equals(o.id);
	}

}
