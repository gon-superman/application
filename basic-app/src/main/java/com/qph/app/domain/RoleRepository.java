package com.qph.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qph.app.domain.pojo.SysRole;


public interface RoleRepository extends JpaRepository<SysRole, Long>{

}
