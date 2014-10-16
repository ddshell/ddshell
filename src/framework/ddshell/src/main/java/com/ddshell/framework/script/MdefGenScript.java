package com.ddshell.framework.script;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.ddshell.framework.common.util.FreemarkerUtils;
import com.ddshell.framework.script.entity.MdefBody;
import com.ddshell.framework.script.entity.MdefMain;
import com.ddshell.framework.script.entity.context.MdefField;
import com.ddshell.framework.script.entity.context.MdefFieldGroup;
import com.ddshell.framework.script.entity.context.MdefMessage;
import com.ddshell.framework.script.entity.context.MdefRoot;
import com.ddshell.framework.script.repository.MdefMainRepository;
import com.ddshell.framework.script.util.ApplicationContextUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class MdefGenScript {

	private static final String ENCODING = "UTF-8";

	protected static void execute(Class<? extends MdefGenScript> scriptClass) {
		ApplicationContextUtils.getBean(scriptClass).execute();
	}

	protected static String getURI(String path) {
		try {
			File projectDir = ResourceUtils
					.getFile("classpath:application.properties")
					.getParentFile().getParentFile().getParentFile();
			return new File(projectDir, path).toURI().toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Autowired
	private MdefMainRepository mainRepository;

	protected void execute() {
		try {
			String templatesLocation = getURI("src/mdef");
			String templatesSrc = "src";
			String outputDir = "./";

			File targetRoot = new File(outputDir);
			String templatesPattern = templatesLocation + "/" + templatesSrc
					+ "/**/*";
			int pathStart = templatesSrc.length() + 1;

			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource templates = resolver.getResource(templatesLocation);

			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(templates.getFile());

			String templatesURI = templates.getURI().getPath();

			MdefRoot root = getRoot();
			String lib = buildInlineLib();

			for (Resource resource : resolver.getResources(templatesPattern)) {
				File srcFile = resource.getFile();

				if (srcFile.isDirectory()) {
					continue;
				}

				String relativeURI = resource.getURI().getPath()
						.substring(templatesURI.length());

				if (isTemplate(relativeURI)) {
					Template template = configuration.getTemplate(relativeURI,
							ENCODING);
					if (isRootTemplate(relativeURI)) {
						String path = FreemarkerUtils.renderString(relativeURI,
								root);
						File out = new File(targetRoot, path.substring(
								pathStart, path.length() - 9));
						String text = FreemarkerUtils.renderTemplate(template,
								root);

						out.getParentFile().mkdirs();
						FileCopyUtils.copy(text.getBytes(ENCODING), out);
					} else {
						for (MdefMessage message : root.getMessages()) {
							String path = FreemarkerUtils.renderString(lib
									+ relativeURI, message);
							File out = new File(targetRoot, path.substring(
									pathStart, path.length() - 4));
							String text = FreemarkerUtils.renderTemplate(
									template, message);

							out.getParentFile().mkdirs();
							FileCopyUtils.copy(text.getBytes(ENCODING), out);
						}
					}
				} else {
					File out = new File(targetRoot,
							FreemarkerUtils.renderString(
									relativeURI.substring(pathStart), root));
					out.getParentFile().mkdirs();

					if (out.isHidden()) {
						out.createNewFile();
					}

					FileCopyUtils.copy(srcFile, out);
				}
			}
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}

	protected abstract String getJavaPackage();

	protected abstract String getProject();

	private String buildInlineLib() {
		StringBuffer buf = new StringBuffer();
		buf.append("<#function upper_case str><#return str?upper_case></#function>");
		buf.append("<#function lower_case str><#return str?lower_case></#function>");
		buf.append("<#function cap_first str><#return str?cap_first></#function>");
		return buf.toString();
	}

	private boolean isTemplate(String relativeURI) {
		return relativeURI.endsWith(".ftl");
	}

	private boolean isRootTemplate(String relativeURI) {
		return relativeURI.endsWith(".root.ftl");
	}

	private MdefRoot getRoot() {
		MdefRoot root = new MdefRoot();
		root.setJavaPackage(getJavaPackage());
		root.setProject(getProject());

		List<MdefMessage> messages = new ArrayList<MdefMessage>();
		root.setMessages(messages);

		for (MdefMain msg : mainRepository.findAll()) {
			MdefMessage message = new MdefMessage();
			messages.add(message);

			message.setRoot(root);
			message.setId(msg.getId());
			message.setEncrypt("Y".equalsIgnoreCase(msg.getIsEncrypt()));
			message.setSign("Y".equalsIgnoreCase(msg.getIsSign()));
			message.setAnon("Y".equalsIgnoreCase(msg.getIsAnon()));
			message.setAuthcform("Y".equalsIgnoreCase(msg.getIsAuthcform()));
			message.setDescription(msg.getDescription());
			message.setRequestFields(new ArrayList<MdefField>());
			message.setRequestGroups(new ArrayList<MdefFieldGroup>());
			message.setResponseFields(new ArrayList<MdefField>());
			message.setResponseGroups(new ArrayList<MdefFieldGroup>());

			buildFieldsAndGroups(
					new ArrayList<MdefBody>(msg.getRequestElements()),
					message.getRequestFields(), message.getRequestGroups());
			buildFieldsAndGroups(
					new ArrayList<MdefBody>(msg.getResponseElements()),
					message.getResponseFields(), message.getResponseGroups());
		}

		return root;
	}

	private void buildFieldsAndGroups(List<MdefBody> elems,
			List<MdefField> fields, List<MdefFieldGroup> groups) {
		Map<String, MdefFieldGroup> groupMap = new LinkedHashMap<String, MdefFieldGroup>();
		for (MdefBody elem : elems) {
			String groupId = elem.getGroupId();
			if (groupId == null || "".equals(groupId)) {
				appendToFields(elem, fields);
			} else {
				MdefFieldGroup group = groupMap.get(groupId);
				if (group == null) {
					group = new MdefFieldGroup();
					groupMap.put(groupId, group);

					group.setId(groupId);
					group.setDescription(elem.getGroupDescription());
					group.setFields(new ArrayList<MdefField>());
				}
				appendToFields(elem, group.getFields());
			}
		}
		groups.addAll(groupMap.values());
	}

	private void appendToFields(MdefBody elem, List<MdefField> fields) {
		MdefField field = new MdefField();
		fields.add(field);

		field.setId(elem.getId());
		field.setDescription(elem.getDescription());
		field.setValueConst(elem.getValueConst());
		field.setValueExample(elem.getValueExample());
		field.setDbType(elem.getValueType().getDbType());
		field.setJavaType(elem.getValueType().getJavaType());
		field.setObjcType(elem.getValueType().getObjcType());
		field.setValueLength(elem.getValueLength());
		field.setValueScale(elem.getValueScale());
		if (isVa(elem.getVaId1())) {
			field.setVaId1(elem.getVaId1());
			field.setVaParams1(elem.getVaParams1());
		}
		if (isVa(elem.getVaId2())) {
			field.setVaId2(elem.getVaId2());
			field.setVaParams2(elem.getVaParams2());
		}
	}

	private boolean isVa(String vaId) {
		return vaId != null && vaId.startsWith("@");
	}

}
