package com.qph.app.service;

import org.springframework.stereotype.Service;

import com.qph.app.common.BaseService;
import com.qph.app.domain.RoleRepository;
import com.qph.app.domain.pojo.SysRole;

@Service
public class RoleService extends BaseService<SysRole, Long, RoleRepository> {

}
