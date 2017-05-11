package com.qph.app.service;

import com.qph.app.domain.pojo.SysUser;

public interface UserService {

	SysUser findUserByUsername(String username);
}
