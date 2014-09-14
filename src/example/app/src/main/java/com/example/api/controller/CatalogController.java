package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddshell.framework.api.entity.RemoteMessage;
import com.ddshell.framework.api.service.MessageService;
import com.ddshell.framework.api.service.RestService;
import com.example.api.API;
import com.example.app.service.CatalogService;

@Controller
public class CatalogController {

	@Autowired
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
