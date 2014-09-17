package com.example.script.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbExportScript;
import com.example.security.entity.Menu;
import com.example.security.repository.MenuRepository;

@Transactional(readOnly = true)
@Component
public class MenuExportScript extends DbExportScript {

	@Autowired
	private MenuRepository menuRep;

	public static void main(String[] args) throws Exception {
		export(MenuExportScript.class);
	}

	@Override
	public void export() throws Exception {
		export(menuRep.findByLevelOrderByDispOrderAsc(1), Menu.class);
	}

}
