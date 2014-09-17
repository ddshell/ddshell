package com.example.script.exp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbExportScript;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;

@Transactional(readOnly = true)
@Component
public class UserExportScript extends DbExportScript {

	@Autowired
	private UserRepository userRep;

	public static void main(String[] args) throws Exception {
		export(UserExportScript.class);
	}

	@Override
	public void export() throws Exception {
		export(userRep.findAll(), User.class);
	}

}
