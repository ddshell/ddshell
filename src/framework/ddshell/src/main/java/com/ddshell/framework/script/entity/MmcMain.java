package com.ddshell.framework.script.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mmc_main")
public class MmcMain {

	@Id
	private String id;
	private String classific;
	private String description;
	private String isEncrypt;
	private String isSign;
	private String isAnon;
	private String isAuthcform;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "mmcId")
	private List<MmcRequest> requestElements;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "mmcId")
	private List<MmcResponse> responseElements;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassific() {
		return classific;
	}

	public void setClassific(String classific) {
		this.classific = classific;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsEncrypt() {
		return isEncrypt;
	}

	public void setIsEncrypt(String isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	public String getIsSign() {
		return isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}

	public String getIsAnon() {
		return isAnon;
	}

	public void setIsAnon(String isAnon) {
		this.isAnon = isAnon;
	}

	public String getIsAuthcform() {
		return isAuthcform;
	}

	public void setIsAuthcform(String isAuthcform) {
		this.isAuthcform = isAuthcform;
	}

	public List<MmcRequest> getRequestElements() {
		return requestElements;
	}

	public void setRequestElements(List<MmcRequest> requestElements) {
		this.requestElements = requestElements;
	}

	public List<MmcResponse> getResponseElements() {
		return responseElements;
	}

	public void setResponseElements(List<MmcResponse> responseElements) {
		this.responseElements = responseElements;
	}

}