package com.ddshell.framework.script.entity.context;

import java.util.List;

public class MdefRoot {

	private String javaPackage;
	private String project;
	private List<MdefMessage> messages;

	public String getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<MdefMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<MdefMessage> messages) {
		this.messages = messages;
	}

	public String getJavaPackagePath() {
		return javaPackage.replaceAll("\\.", "/");
	}

	public String getObjcPrefix() {
		return project.toUpperCase();
	}
}
