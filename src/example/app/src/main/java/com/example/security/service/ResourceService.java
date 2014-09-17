package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ddshell.framework.common.util.GenericCrudService;
import com.ddshell.framework.security.shiro.service.ShiroResourceService;
import com.example.security.entity.Resource;
import com.example.security.repository.ResourceRepository;

@Transactional(readOnly = true)
@Component
public class ResourceService extends GenericCrudService<Resource, Long>
		implements ShiroResourceService {

	@Autowired
	private ResourceRepository resourceRep;

	@Override
	protected JpaRepository<Resource, Long> getRepository() {
		return resourceRep;
	}

}
