package com.ddshell.framework.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddshell.framework.security.entity.Menu;

public interface MenuRepository<T extends Menu> extends JpaRepository<T, Long> {

	public List<T> findByLevelOrderByDispOrderAsc(Integer level);

}
