package com.qph.app.service;

import org.springframework.stereotype.Service;

import com.qph.app.common.BaseService;
import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysUser;

@Service
public class UserService extends BaseService<SysUser , Long, UserRepository>{

	public SysUser findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
