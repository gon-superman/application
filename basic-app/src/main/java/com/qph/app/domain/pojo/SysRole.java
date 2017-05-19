package com.qph.app.domain.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SysRole implements Serializable{

	private static final long serialVersionUID = 6548799977501575573L;

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	private String description;

	@ManyToMany(mappedBy="roleList")//由SysUser方来维护关联关系
	private Set<SysUser> userList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(Set<SysUser> userList) {
		this.userList = userList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
