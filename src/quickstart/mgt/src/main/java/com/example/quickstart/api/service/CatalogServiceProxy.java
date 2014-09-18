package com.example.quickstart.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddshell.framework.remote.service.RestService;
import com.example.quickstart.api.API;
import com.example.quickstart.api.AppVars;
import com.example.quickstart.app.entity.Catalog;
import com.example.quickstart.app.service.CatalogService;

@Component
public class CatalogServiceProxy implements CatalogService {

	@Autowired
	private AppVars appVars;
	@Autowired
	private RestService rest;

	@SuppressWarnings("unchecked")
	@Override
	public List<Catalog> findByName(String name) {
		return (ArrayList<Catalog>) rest.postX(RestService.SESSION_NONE,
				appVars.appServer + API.APP_CATALOG_FIND_BY_NAME, name,
				ArrayList.class, Catalog.class);
	}

}
