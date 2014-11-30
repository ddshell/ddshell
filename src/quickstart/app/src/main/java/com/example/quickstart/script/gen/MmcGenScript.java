package com.example.quickstart.script.gen;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Component
public class MmcGenScript extends com.ddshell.framework.script.MmcGenScript {

	public static void main(String[] args) {
		System.setProperty("mmc.sql.location", getURI("src/mmc/sql"));

		execute(MmcGenScript.class);
	}

	@Override
	protected String getJavaPackage() {
		return "com.example";
	}

	@Override
	protected String getProject() {
		return "quickstart";
	}

}
