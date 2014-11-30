package com.example.quickstart.script.init;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.script.DbInitScript;
import com.example.quickstart.security.entity.Role;
import com.example.quickstart.security.entity.User;
import com.example.quickstart.security.repository.RoleRepository;
import com.example.quickstart.security.repository.UserRepository;

@Transactional(readOnly = true)
@Component
public class UserInitScript extends DbInitScript {

	@Autowired
	private UserRepository userRep;
	@Autowired
	private RoleRepository roleRep;

	public static void main(String[] args) throws Exception {
		init(UserInitScript.class);
	}

	@Transactional
	@Override
	public void init(File datafile) throws Exception {
		userRep.deleteAllInBatch();

		List<User> values = load(datafile, User.class);
		for (User user : values) {
			user = userRep.save(user);
			for (String roleName : user.getRoleNames()) {
				Role role = roleRep.findByName(roleName);
				if (role != null) {
					user.getRoles().add(role);
					userRep.save(user);
				}
			}
		}

	}
}
