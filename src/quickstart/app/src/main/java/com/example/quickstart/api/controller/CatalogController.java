package com.example.quickstart.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddshell.framework.remote.entity.RemoteMessage;
import com.ddshell.framework.remote.service.MessageService;
import com.ddshell.framework.remote.service.RestService;
import com.example.quickstart.api.API;
import com.example.quickstart.app.service.CatalogService;

@Controller
public class CatalogController {

	@Autowired
	@Qualifier("restService")
	private RestService rest;
	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value = API.APP_CATALOG_FIND_BY_NAME, method = RequestMethod.POST)
	@ResponseBody
	public RemoteMessage findByName(@RequestBody RemoteMessage message) {
		return rest.service(message, new MessageService() {
			@Override
			public Object execute(Object request) {
				return catalogService.findByName((String) request);
			}
		}, String.class);
	}

}
