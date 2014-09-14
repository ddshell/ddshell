package com.example.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppVars {

	@Value("${server.mgt:http://127.0.0.1:8080}")
	public String mgtServer;

	@Value("${server.app:http://127.0.0.1:9080}")
	public String appServer;

}
