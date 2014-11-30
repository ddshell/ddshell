package com.example.quickstart.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.quickstart.api.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = Schema.T_APP_USER)
@Entity
public class User extends UserEntity implements Serializable {

	private static final long serialVersionUID = -1885973931994846376L;

	private String idcardno;
	private String phonenumber;
	private String realname;

	@Transient
	private Set<String> roleNames;
	@Transient
	private Set<String> permissionNames;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = Schema.T_APP_USERROLE, joinColumns = @JoinColumn(name = Schema.T_USERROLE_COL_USERID), inverseJoinColumns = @JoinColumn(name = Schema.T_USERROLE_COL_ROLEID))
	@JsonIgnore
	private Set<Role> roles = new HashSet<Role>();

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<String> getRoleNames() {
		if (roleNames == null) {
			roleNames = new HashSet<String>();
			if (getRoles() != null) {
				for (Role role : getRoles()) {
					roleNames.add(role.getName());
				}
			}
		}
		return roleNames;
	}

	public Set<String> getPermissionNames() {
		return permissionNames;
	}

}