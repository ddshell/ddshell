package com.ddshell.framework.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ddshell.framework.security.entity.Resource;

public interface ResourceRepository<T extends Resource> extends
		JpaRepository<T, Long> {

	@Query("SELECT r FROM Resource r ORDER BY r.id ASC")
	public List<T> findAll();
}
