package com.ddshell.framework.script;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.ddshell.framework.script.util.ApplicationContextUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DbInitScript {

	protected static void init(Class<? extends DbInitScript> scriptClass)
			throws Exception {
		File projectDir = ResourceUtils
				.getFile("classpath:application.properties").getParentFile()
				.getParentFile().getParentFile();
		File webinf = new File(projectDir, "src/main/webapp/WEB-INF/");

		ApplicationContextUtils.getBean(scriptClass).init(
				getDataFile(webinf, scriptClass));
	}

	protected static <T> List<T> load(File datafile, Class<T> valueType)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory()
				.constructParametricType(ArrayList.class, valueType);

		return objectMapper.<List<T>> readValue(datafile, javaType);
	}

	private static File getDataFile(File webinf,
			Class<? extends DbInitScript> scriptClass) {
		String className = scriptClass.getSimpleName();
		return new File(new File(webinf, "data"), className.replaceAll(
				"InitScript$", "").toLowerCase()
				+ ".txt");
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		try {
			File webinf = ResourceUtils
					.getFile("classpath:application.properties")
					.getParentFile().getParentFile();
			File datafile = getDataFile(webinf, getClass());
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
