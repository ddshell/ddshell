package com.example.script.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbExportScript;
import com.example.security.entity.Resource;
import com.example.security.repository.ResourceRepository;

@Transactional(readOnly = true)
@Component
public class ResourceExportScript extends DbExportScript {

	@Autowired
	private ResourceRepository resourceRep;

	public static void main(String[] args) throws Exception {
		export(ResourceExportScript.class);
	}

	@Override
	public void export() throws Exception {
		export(resourceRep.findAll(), Resource.class);
	}

}
