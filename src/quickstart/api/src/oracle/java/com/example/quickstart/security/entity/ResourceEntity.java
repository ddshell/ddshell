package com.example.quickstart.security.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.ddshell.framework.dialect.oracle.OracleSchema;
import com.ddshell.framework.security.entity.Resource;
import com.example.quickstart.api.Schema;

@MappedSuperclass
public abstract class ResourceEntity extends Resource {

	public static final String SEQUENCE = Schema.T_APP_RESOURCE
			+ OracleSchema.SEQ_SUFFIX;

	@SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
