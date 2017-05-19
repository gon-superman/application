package com.qph.app.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * <p>
 *   <h3>基础框架<h3>
 *   说明：CRUD基本操作
 * <p>
 * @author <a href="mailto:waterlord@vip.qq.com">qupenghui</a>
 * @version 2017年5月19日下午1:54:23
 */
public abstract class BaseService<T, ID extends Serializable , JPA extends JpaRepository<T, ID>>  {

	@Autowired
	protected JPA repository;
	
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}

	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	public List<T> findAll(Iterable<ID> ids) {
		return repository.findAll(ids);
	}

	public List<T> save(Iterable<T> entities) {
		return repository.save(entities);
	}

	public  T save(T entity) {
		return repository.save(entity);
	}

	public T getOne(ID id) {
		return repository.getOne(id);
	}
	
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	public long count() {
		return repository.count();
	}

	public void delete(ID id) {
		repository.delete(id);
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	public void delete(Iterable<T> entities) {
		repository.deleteInBatch(entities);
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}


}
