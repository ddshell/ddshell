package com.example.security.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.security.entity.Menu;

public interface MenuRepository extends
		com.ddshell.framework.security.repository.MenuRepository<Menu> {

	@Modifying
	@Query("update Menu m set m.parent = null")
	public int setParentToNull();

}
