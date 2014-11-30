package ${javaPackage}.${project}.mobile.gen.controller;

import com.ddshell.framework.mobile.service.AuthcFormService;
import com.ddshell.framework.mobile.service.MessageService;
import com.ddshell.framework.mobile.service.MobileService;
import com.fasterxml.jackson.databind.ObjectMapper;

<#list messages as message>
import ${javaPackage}.${project}.mobile.gen.protocol.${message.name}Request;
import ${javaPackage}.${project}.mobile.gen.protocol.${message.name}Response;
</#list>
<#list messages as message>
import ${javaPackage}.${project}.mobile.gen.service.${message.name}Service;
</#list>

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/mobile")
@Controller
public class MobileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(MobileController.class);

<#list messages as message>
	@Qualifier("${message.id}Service")
	@Autowired(required = false)
	<#if message.authcform>AuthcFormService<${message.name}Request, ${message.name}Response><#else>MessageService<${message.name}Request, ${message.name}Response></#if> ${message.id}Service;
</#list>

	@Autowired
	MobileService mobileService;

	ObjectMapper objectMapper = new ObjectMapper();

<#list messages as message>

	@RequestMapping(value = "/${message.id}", method = RequestMethod.POST)
	@ResponseBody
	public ${message.name}Response ${message.id}(@RequestBody ${message.name}Request request) {
		debug(request);
		${message.name}Response response = get${message.name}Response(request);
		debug(response);

		return response;
	}
</#list>

<#list messages as message>

	private ${message.name}Response get${message.name}Response(${message.name}Request request) {
		mobileService.bindSubject(request.getSessionId());

		if (${message.id}Service == null) {
			${message.name}Response response = new ${message.name}Response();
  <#if message.authcform>
			new ${message.name}Service().onSuccess(request, response);
  <#else>
			new ${message.name}Service().execute(request, response);
  </#if>
			return response;
		}

  <#if message.authcform>
		return mobileService.serviceForAuthcForm(request, ${message.id}Service,
				${message.name}Request.class, ${message.name}Response.class);
  <#elseif message.anon>
		return mobileService.serviceForAnon(request, ${message.id}Service,
				${message.name}Request.class, ${message.name}Response.class);
  <#else>
		return mobileService.serviceForAuthc(request, ${message.id}Service,
				${message.name}Request.class, ${message.name}Response.class);
  </#if>
	}
</#list>

	private void debug(Object obj) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(objectMapper.writeValueAsString(obj));
			}
		} catch (Throwable t) {
			LOG.error(t.getMessage(), t);
		}
	}

}
