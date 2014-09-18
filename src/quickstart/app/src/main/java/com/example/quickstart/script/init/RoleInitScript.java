package com.example.quickstart.script.init;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbInitScript;
import com.example.quickstart.security.entity.Role;
import com.example.quickstart.security.repository.RoleRepository;
import com.example.quickstart.security.repository.UserRepository;

@Transactional(readOnly = true)
@Component
public class RoleInitScript extends DbInitScript {

	@Autowired
	private UserRepository userRep;
	@Autowired
	private RoleRepository roleRep;

	public static void main(String[] args) throws Exception {
		init(RoleInitScript.class);
	}

	@Transactional
	@Override
	public void init(File datafile) throws Exception {
		userRep.deleteAllInBatch();
		roleRep.deleteAllInBatch();

		roleRep.save(load(datafile, Role.class));
	}
}
