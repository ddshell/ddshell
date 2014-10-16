package com.ddshell.framework.script.entity.context;

import java.util.List;

public class MdefFieldGroup {

	private String id;
	private String description;
	private List<MdefField> fields;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MdefField> getFields() {
		return fields;
	}

	public void setFields(List<MdefField> fields) {
		this.fields = fields;
	}
}
