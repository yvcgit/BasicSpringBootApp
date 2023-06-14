package com.developerslike.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developerslike.entity.Resource;

@Repository
public interface IResourceDao extends CrudRepository<Resource, Long> {

	public Optional<Resource> findById(long id);

	public boolean existsByResourceId(String resourceId);

	public boolean existsByResourceName(String resourceName);
}
