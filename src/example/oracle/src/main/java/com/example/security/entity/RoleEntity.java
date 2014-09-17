package com.example.security.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.ddshell.framework.dialect.oracle.OracleSchema;
import com.ddshell.framework.security.entity.Role;
import com.example.api.Schema;

@MappedSuperclass
public abstract class RoleEntity extends Role {

	public static final String SEQUENCE = Schema.T_APP_ROLE
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
