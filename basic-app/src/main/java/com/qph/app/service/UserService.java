package com.qph.app.service;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qph.app.common.BaseService;
import com.qph.app.domain.UserRepository;
import com.qph.app.domain.pojo.SysUser;

@Service
@CacheConfig(cacheNames="users")
public class UserService extends BaseService<SysUser , Long, UserRepository>{

	public SysUser findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Cacheable
	public List<SysUser> findAll() {
		return super.findAll();
	}

	@CacheEvict (key="#p0")
	public void delete(Long id) {
		super.delete(id);
	}

	@CachePut (key="#p0.id")
	public SysUser save(SysUser entity) {
		return super.save(entity);
	}

	@Cacheable (key="#p0")
	public SysUser getOne(Long id) {
		return super.getOne(id);
	}
	
	
}
