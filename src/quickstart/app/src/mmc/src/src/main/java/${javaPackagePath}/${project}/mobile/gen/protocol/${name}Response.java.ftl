package ${javaPackage}.${project}.mobile.gen.protocol;
<#if responseGroups?has_content>

import java.util.List;
</#if>

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.ddshell.framework.mobile.entity.MobileMessage;

/**
 * ${description}.服务端响应
 * 
 * @author 代码生成器v1.0
 * 
 */
@JsonInclude(Include.NON_NULL)
public class ${name}Response extends MobileMessage {

<#list responseGroups as group>

	/**
	 * @see ${javaPackage}.${project}.message.protocol.${name}Response#get${group.id?cap_first}
	 * 
	 */
	@JsonInclude(Include.NON_NULL)
	public static class Element${group.id?cap_first} {

	<#list group.fields as field>
		private ${field.javaType} ${field.id};
	</#list>
	<#list group.fields as field>

		/**
		 * @return ${field.description}
		 */
		public ${field.javaType} get${field.id?cap_first}() {
			return ${field.id};
		}

		public void set${field.id?cap_first}(${field.javaType} ${field.id}) {
			this.${field.id} = ${field.id};
		}
	</#list>
	}
</#list>

<#list responseFields as field>
	private ${field.javaType} ${field.id};
</#list>
<#list responseGroups as group>
	private List<Element${group.id?cap_first}> ${group.id};
</#list>

	public ${name}Response() {
		super();
	}

	public ${name}Response(String statusCode, String statusMessage) {
		this.setStatusCode(statusCode);
		this.setStatusMessage(statusMessage);
	}

<#list responseFields as field>

	/**
	 * @return ${field.description}
	 */
	public ${field.javaType} get${field.id?cap_first}() {
		return ${field.id};
	}

	public void set${field.id?cap_first}(${field.javaType} ${field.id}) {
		this.${field.id} = ${field.id};
	}
</#list>
<#list responseGroups as group>

	/**
	 * @return ${group.description}
	 */
	public List<Element${group.id?cap_first}> get${group.id?cap_first}() {
		return ${group.id};
	}

	public void set${group.id?cap_first}(List<Element${group.id?cap_first}> ${group.id}) {
		this.${group.id} = ${group.id};
	}
</#list>
<#if encrypt>
	public boolean isEncrypt() {
		return true;
	}
</#if>
}