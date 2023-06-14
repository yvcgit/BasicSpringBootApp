package com.developerslike.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developerslike.entity.ResourceMapped;

@Repository
public interface IResourceMappedDao extends CrudRepository<ResourceMapped, Long> {

	public Optional<ResourceMapped> findById(long id);

	public boolean existsByProjectIdAndResourceId(String projectId, String resourceId);

}
