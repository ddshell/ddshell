package com.example.quickstart.script.init;

public class DataInitScript {

	public static void main(String[] args) throws Exception {
		CatalogInitScript.main(args);
		ResourceInitScript.main(args);
		MenuInitScript.main(args);
		RoleInitScript.main(args);
		UserInitScript.main(args);
	}
}