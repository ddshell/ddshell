package com.example.quickstart.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.quickstart.security.entity.Menu;
import com.example.quickstart.security.repository.MenuRepository;

@Transactional(readOnly = true)
@Component
public class MenuService extends
		com.ddshell.framework.security.service.MenuService<Menu> {

	@Autowired
	private MenuRepository menuRep;

	@Override
	protected JpaRepository<Menu, Long> getRepository() {
		return menuRep;
	}

}
