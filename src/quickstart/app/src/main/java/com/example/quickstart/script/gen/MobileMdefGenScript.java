package com.example.quickstart.script.gen;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.MdefGenScript;

@Transactional(readOnly = true)
@Component
public class MobileMdefGenScript extends MdefGenScript {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "production,mdef");
		System.setProperty("mdef.sql.location", getURI("src/mdef/sql"));

		execute(MobileMdefGenScript.class);
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
