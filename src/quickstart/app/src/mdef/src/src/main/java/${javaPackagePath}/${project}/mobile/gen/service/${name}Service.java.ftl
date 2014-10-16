package ${javaPackage}.${project}.mobile.gen.service;

<#if responseGroups?has_content>
import java.util.ArrayList;
import java.util.List;
</#if>

<#if authcform>
import com.ddshell.framework.mobile.service.AuthcFormService;
<#else>
import com.ddshell.framework.mobile.service.MessageService;
</#if>
import ${javaPackage}.${project}.mobile.gen.protocol.${name}Request;
import ${javaPackage}.${project}.mobile.gen.protocol.${name}Response;
<#list responseGroups as group>
import ${javaPackage}.${project}.mobile.gen.protocol.${name}Response.Element${group.id?cap_first};
</#list>

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class ${name}Service implements <#if authcform>AuthcFormService<${name}Request, ${name}Response><#else>MessageService<${name}Request, ${name}Response></#if> {

	private static final Logger LOG = LoggerFactory
			.getLogger(${name}Service.class);

<#if authcform>
	@Override
	public String getLoginName(${name}Request request) {
 		return null;
	}

	@Override
	public String getPassword(${name}Request request) {
		return null;
	}

	@Override
	public void onException(${name}Request request, ${name}Response response, Exception e) {
		LOG.error(e.getMessage(), e);
	}

	@Override
	public void onSuccess(${name}Request request, ${name}Response response) {
		LOG.debug("It's work.");

  <#list responseFields as field>
    <#if field.javaType == 'String'>
		response.set${field.id?cap_first}("${field.valueExample?j_string}");
    <#else>
		response.set${field.id?cap_first}(<#if field.valueExample?has_content>${field.valueExample}<#else>0</#if>);
    </#if>
  </#list>
  <#list responseGroups as group>
		response.set${group.id?cap_first}(get${group.id?cap_first}());
  </#list>
	}
<#else>

	@Override
	public void execute(${name}Request request, ${name}Response response) {
		LOG.debug("It's work.");

  <#list responseFields as field>
    <#if field.javaType == 'String'>
		response.set${field.id?cap_first}("${field.valueExample?j_string}");
    <#else>
		response.set${field.id?cap_first}(<#if field.valueExample?has_content>${field.valueExample}<#else>0</#if>);
    </#if>
  </#list>
  <#list responseGroups as group>
		response.set${group.id?cap_first}(get${group.id?cap_first}());
  </#list>
	}

</#if>
<#list responseGroups as group>

	private List<Element${group.id?cap_first}> get${group.id?cap_first}() {
		List<Element${group.id?cap_first}> elems = new ArrayList<Element${group.id?cap_first}>();
		Element${group.id?cap_first} elem = new Element${group.id?cap_first}();
		elems.add(elem);

	<#list group.fields as field>
		<#if field.javaType == 'String'>
		elem.set${field.id?cap_first}("${field.valueExample?j_string}");
		<#else>
		elem.set${field.id?cap_first}(<#if field.valueExample?has_content>${field.valueExample}<#else>0</#if>);
		</#if>
	</#list>

		return elems;
	}
</#list>
}
