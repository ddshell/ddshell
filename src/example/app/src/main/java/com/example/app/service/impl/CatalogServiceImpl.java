package com.example.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ddshell.framework.common.util.GenericCrudService;
import com.example.app.entity.Catalog;
import com.example.app.repository.CatalogRepository;
import com.example.app.service.CatalogService;

@Component
public class CatalogServiceImpl extends GenericCrudService<Catalog, Long>
		implements CatalogService {

	@Autowired
	private CatalogRepository catalogRep;

	@Override
	protected JpaRepository<Catalog, Long> getRepository() {
		return catalogRep;
	}

	@Override
	public List<Catalog> findByName(String name) {
		return catalogRep.findByName(name);
	}

}
