package com.ddshell.framework.script;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.ddshell.framework.script.util.ApplicationContextUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DbInitScript {

	protected static void init(Class<? extends DbInitScript> scriptClass)
			throws Exception {
		ApplicationContextUtils.setActiveProfiles("production");
		ApplicationContextUtils.getBean(scriptClass).init();
	}

	protected static <T> List<T> load(File datafile, Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(ArrayList.class, valueType);

		return objectMapper.<List<T>> readValue(datafile, javaType);
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Transactional
	public void init() {
		try {
			String name = getClass().getSimpleName()
					.replaceAll("InitScript$", "").toLowerCase()
					+ ".txt";
			File datafile = ResourceUtils.getFile("classpath:initdata/" + name);
			if (datafile.exists()) {
				init(datafile);
				datafile.delete();
			}
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
		}
	}

	public abstract void init(File datafile) throws Exception;

}
