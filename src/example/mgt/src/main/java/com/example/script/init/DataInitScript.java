package com.example.script.init;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn({ "resourceInitScript", "menuInitScript", "roleInitScript",
		"userInitScript" })
@Component
public class DataInitScript {

	public static void main(String[] args) throws Exception {
		ResourceInitScript.main(args);
		MenuInitScript.main(args);
		RoleInitScript.main(args);
		UserInitScript.main(args);
	}
}