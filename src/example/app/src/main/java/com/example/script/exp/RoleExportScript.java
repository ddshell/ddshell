package com.example.script.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbExportScript;
import com.example.security.entity.Role;
import com.example.security.repository.RoleRepository;

@Transactional(readOnly = true)
@Component
public class RoleExportScript extends DbExportScript {

	@Autowired
	private RoleRepository roleRep;

	public static void main(String[] args) throws Exception {
		export(RoleExportScript.class);
	}

	@Override
	public void export() throws Exception {
		export(roleRep.findAll(), Role.class);
	}

}
