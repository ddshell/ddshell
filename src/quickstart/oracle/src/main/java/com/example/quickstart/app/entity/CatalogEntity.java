package com.example.quickstart.app.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.ddshell.framework.dialect.oracle.OracleSchema;
import com.ddshell.framework.dialect.oracle.SimpleEntity;
import com.example.quickstart.api.Schema;

@MappedSuperclass
public abstract class CatalogEntity extends SimpleEntity {

	public static final String SEQUENCE = Schema.T_APP_CATALOG
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
