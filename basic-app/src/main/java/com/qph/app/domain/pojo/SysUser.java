package com.qph.app.domain.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class SysUser implements Serializable{

	private static final long serialVersionUID = 4741357898226268233L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false,unique=true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private boolean enabled;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="sys_user_role", joinColumns={ @JoinColumn(name="uid")}, inverseJoinColumns={ @JoinColumn(name = "rid") })
	private Set<SysRole> roleList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<SysRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(Set<SysRole> roleList) {
		this.roleList = roleList;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
