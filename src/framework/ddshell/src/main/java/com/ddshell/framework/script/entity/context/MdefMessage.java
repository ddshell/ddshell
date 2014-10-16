package com.ddshell.framework.script.entity.context;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

public class MdefMessage {

	private String id;
	private String description;
	private boolean encrypt;
	private boolean sign;
	private boolean anon;
	private boolean authcform;
	private List<MdefField> requestFields;
	private List<MdefFieldGroup> requestGroups;
	private List<MdefField> responseFields;
	private List<MdefFieldGroup> responseGroups;
	private MdefRoot root;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return StringUtils.capitalize(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}

	public boolean isAnon() {
		return anon;
	}

	public void setAnon(boolean anon) {
		this.anon = anon;
	}

	public boolean isAuthcform() {
		return authcform;
	}

	public void setAuthcform(boolean autchcform) {
		this.authcform = autchcform;
	}

	public List<MdefField> getRequestFields() {
		return requestFields;
	}

	public void setRequestFields(List<MdefField> requestFields) {
		this.requestFields = requestFields;
	}

	public List<MdefFieldGroup> getRequestGroups() {
		return requestGroups;
	}

	public void setRequestGroups(List<MdefFieldGroup> requestGroups) {
		this.requestGroups = requestGroups;
	}

	public List<MdefField> getResponseFields() {
		return responseFields;
	}

	public void setResponseFields(List<MdefField> responseFields) {
		this.responseFields = responseFields;
	}

	public List<MdefFieldGroup> getResponseGroups() {
		return responseGroups;
	}

	public void setResponseGroups(List<MdefFieldGroup> responseGroups) {
		this.responseGroups = responseGroups;
	}

	public Set<String> getValidators() {
		Set<String> validators = new HashSet<String>();
		for (MdefField field : requestFields) {
			if (field.getVaId1() != null) {
				validators.add(field.getVaId1().substring(1));
			}
			if (field.getVaId2() != null) {
				validators.add(field.getVaId2().substring(1));
			}
		}
		for (MdefFieldGroup group : requestGroups) {
			for (MdefField field : group.getFields()) {
				if (field.getVaId1() != null) {
					validators.add(field.getVaId1().substring(1));
				}
				if (field.getVaId2() != null) {
					validators.add(field.getVaId2().substring(1));
				}
			}
		}

		return validators;
	}

	public String getJavaPackage() {
		return root.getJavaPackage();
	}

	public String getJavaPackagePath() {
		return root.getJavaPackagePath();
	}

	public String getProject() {
		return root.getProject();
	}

	public String getObjcPrefix() {
		return root.getObjcPrefix();
	}

	public MdefMessage getMessage() {
		return this;
	}

	public MdefRoot getRoot() {
		return root;
	}

	public void setRoot(MdefRoot root) {
		this.root = root;
	}

}
