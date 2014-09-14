package com.example.app.script.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.app.script.DbExportScript;
import com.example.app.entity.Catalog;
import com.example.app.repository.CatalogRepository;

@Transactional(readOnly = true)
@Component
public class CatalogExportScript extends DbExportScript {

	@Autowired
	private CatalogRepository catalogRep;

	public static void main(String[] args) throws Exception {
		export(CatalogExportScript.class);
	}

	@Override
	public void export() throws Exception {
		export(catalogRep.findByParentOrderByDispOrderAsc(null), Catalog.class);
	}

}
