package com.example.app.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.example.app.entity.Catalog;

@ActiveProfiles("test")
@ContextConfiguration(locations = "classpath*:/com/ddshell/spring/*.xml")
public class CatalogServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private CatalogService catalogService;

	@Test
	public void test() {
		List<Catalog> sysVars = catalogService.findByName("sysVars");
		assertNotNull(sysVars);
	}

}
