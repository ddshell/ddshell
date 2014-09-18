package com.example.quickstart.security.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.quickstart.api.Schema;
import com.example.quickstart.security.entity.RoleEntity;

@Entity
@Table(name = Schema.T_APP_ROLE)
public class Role extends RoleEntity implements Serializable {

	private static final long serialVersionUID = -4002009873204022088L;

}