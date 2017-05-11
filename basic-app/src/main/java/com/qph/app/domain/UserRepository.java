package com.qph.app.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qph.app.domain.pojo.SysUser;

@CacheConfig(cacheNames="sysUser")
public interface UserRepository extends JpaRepository<SysUser, Long>{

	@Cacheable(key = "#p0")
	@Query("  from SysUser as u where username=:username")
	SysUser findByUsername(@Param(value = "username") String username);
}
