package com.example.quickstart.app.service;

import java.util.List;

import com.example.quickstart.app.entity.Catalog;

public interface CatalogService {

	public List<Catalog> findByName(String name);

}