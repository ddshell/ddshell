package com.example.quickstart.security.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.example.quickstart.api.Schema;
import com.example.quickstart.security.entity.MenuEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = Schema.T_MGT_MENU)
public class Menu extends MenuEntity implements Serializable {

	private static final long serialVersionUID = -9164309565878896182L;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	@OrderBy("dispOrder")
	private List<Menu> children;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	@JsonIgnore
	private Menu parent;

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

}