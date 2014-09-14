package com.example.app.service;

import java.util.List;

import com.example.app.entity.Catalog;

public interface CatalogService {

	public abstract List<Catalog> findByName(String name);

}