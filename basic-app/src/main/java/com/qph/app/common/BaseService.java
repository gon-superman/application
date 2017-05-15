package com.qph.app.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T , ID extends Serializable> implements InitializingBean {

	protected JpaRepository<T, ID> repository;
	
	public  abstract void setRepository();

	public void afterPropertiesSet() throws Exception {
		setRepository();
	}
	
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

	public <S extends T> List<S> save(Iterable<S> entities) {
		return repository.save(entities);
	}


	public <S extends T> S saveAndFlush(S entity) {
		return repository.saveAndFlush(entity);
	}

	public void deleteInBatch(Iterable<T> entities) {
		repository.deleteInBatch(entities);
	}

	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}

	public T getOne(ID id) {
		return repository.getOne(id);
	}

	public <S extends T> List<S> findAll(Example<S> example) {
		return repository.findAll(example) ;
	}

	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return repository.findAll(example, sort);
	}

}
