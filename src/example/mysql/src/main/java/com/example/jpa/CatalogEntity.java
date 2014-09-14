package com.example.jpa;

import javax.persistence.MappedSuperclass;

import com.ddshell.framework.jpa.mysql.SimpleEntity;
import com.example.jpa.Schema;

@MappedSuperclass
public abstract class CatalogEntity extends SimpleEntity {

	public static final String TABLE = Schema.T_CATALOG;

}
