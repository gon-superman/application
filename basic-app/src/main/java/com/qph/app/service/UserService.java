package com.qph.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qph.app.common.BaseService;
import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysUser;

@Service
public class UserService extends BaseService<SysUser , Long>{

	@Autowired
	UserRepository repository;
	
	@Override
	public void setRepository() {
		super.repository = repository;
	}

}
