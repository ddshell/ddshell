package com.example.quickstart.script.init;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbInitScript;
import com.example.quickstart.security.entity.Menu;
import com.example.quickstart.security.repository.MenuRepository;

@Transactional(readOnly = true)
@Component
public class MenuInitScript extends DbInitScript {

	@Autowired
	private MenuRepository menuRep;

	public static void main(String[] args) throws Exception {
		init(MenuInitScript.class);
	}

	@Transactional
	@Override
	public void init(File datafile) throws Exception {
		menuRep.setParentToNull();
		menuRep.deleteAllInBatch();

		List<Menu> values = load(datafile, Menu.class);
		for (Menu menu : values) {
			setParent(menu);
		}
		menuRep.save(values);
	}

	private void setParent(Menu menu) {
		for (Menu child : menu.getChildren()) {
			child.setParent(menu);
			setParent(child);
		}
	}

}
