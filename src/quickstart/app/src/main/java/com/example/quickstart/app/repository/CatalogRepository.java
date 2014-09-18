package com.example.quickstart.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.quickstart.app.entity.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

	public List<Catalog> findByParentOrderByDispOrderAsc(Catalog parent);

	public List<Catalog> findByName(String name);

	@Modifying
	@Query("update Catalog c set c.parent = null")
	public int setParentToNull();

}
