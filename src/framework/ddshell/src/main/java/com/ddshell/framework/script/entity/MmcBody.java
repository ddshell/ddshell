package com.ddshell.framework.script.entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@IdClass(MmcBodyPK.class)
@MappedSuperclass
public abstract class MmcBody {

	@Id
	private String mmcId;
	@Id
	private String id;
	private String description;
	private String groupId;
	private String groupDescription;
	private String valueConst;
	private String valueExample;
	private int valueLength;
	private int valueScale;
	private String vaId1;
	private String vaParams1;
	private String vaId2;
	private String vaParams2;
	@ManyToOne
	private MmcValueType valueType;

	public String getMmcId() {
		return mmcId;
	}

	public void setMmcId(String mmcId) {
		this.mmcId = mmcId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public MmcValueType getValueType() {
		return valueType;
	}

	public void setValueType(MmcValueType valueType) {
		this.valueType = valueType;
	}

	public String getValueConst() {
		return valueConst;
	}

	public void setValueConst(String valueConst) {
		this.valueConst = valueConst;
	}

	public String getValueExample() {
		return valueExample;
	}

	public void setValueExample(String valueExample) {
		this.valueExample = valueExample;
	}

	public int getValueLength() {
		return valueLength;
	}

	public void setValueLength(int valueLength) {
		this.valueLength = valueLength;
	}

	public int getValueScale() {
		return valueScale;
	}

	public void setValueScale(int valueScale) {
		this.valueScale = valueScale;
	}

	public String getVaId1() {
		return vaId1;
	}

	public void setVaId1(String vaId1) {
		this.vaId1 = vaId1;
	}

	public String getVaParams1() {
		return vaParams1;
	}

	public void setVaParams1(String vaParams1) {
		this.vaParams1 = vaParams1;
	}

	public String getVaId2() {
		return vaId2;
	}

	public void setVaId2(String vaId2) {
		this.vaId2 = vaId2;
	}

	public String getVaParams2() {
		return vaParams2;
	}

	public void setVaParams2(String vaParams2) {
		this.vaParams2 = vaParams2;
	}

}