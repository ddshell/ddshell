package com.ddshell.framework.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.ddshell.framework.jpa.MenuEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = Menu.TABLE)
public class Menu extends MenuEntity implements Serializable {

	private static final long serialVersionUID = -9164309565878896182L;

	private String text;
	private String url;
	private String style;
	@Column(name = "NODE_LEVEL")
	private Integer level = 1;
	private Integer dispOrder = 9999;
	private Date createTime;
	private Date updateTime;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	@OrderBy("dispOrder")
	private List<Menu> children;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	@JsonIgnore
	private Menu parent;

	@Transient
	private String[] styles;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@PrePersist
	public void prePersist() {
		this.createTime = new Date();
		this.updateTime = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateTime = new Date();
	}

	public String[] getStyles() {
		if (styles == null) {
			if (StringUtils.isEmpty(style)) {
				styles = new String[] {};
			} else {
				styles = style.split(",");
			}
		}
		return styles;
	}

	public void setStyles(String[] styles) {
		this.styles = styles;
	}

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