package com.ddshell.framework.script.entity.context;

import java.util.List;

public class MmcRoot {

	private String javaPackage;
	private String project;
	private List<MmcMessage> messages;

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

	public List<MmcMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<MmcMessage> messages) {
		this.messages = messages;
	}

	public String getJavaPackagePath() {
		return javaPackage.replaceAll("\\.", "/");
	}

	public String getObjcPrefix() {
		return project.toUpperCase();
	}
}
