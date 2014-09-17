package com.example.security.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.api.Schema;
import com.example.security.entity.ResourceEntity;

@Entity
@Table(name = Schema.T_APP_RESOURCE)
public class Resource extends ResourceEntity implements Serializable {

	private static final long serialVersionUID = 3128388338392622348L;

}