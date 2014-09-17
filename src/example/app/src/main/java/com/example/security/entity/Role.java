package com.example.security.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.api.Schema;
import com.example.security.entity.RoleEntity;

@Entity
@Table(name = Schema.T_APP_ROLE)
public class Role extends RoleEntity implements Serializable {

	private static final long serialVersionUID = -4002009873204022088L;

}