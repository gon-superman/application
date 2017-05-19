package com.qph.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qph.app.domain.pojo.SysUser;

public interface UserRepository extends JpaRepository<SysUser, Long>{

	@Query("  from SysUser as u where username=:username")
	SysUser findByUsername(@Param(value = "username") String username);
}
