package com.developerslike.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developerslike.entity.Project;

@Repository
public interface IProjectDao extends CrudRepository<Project, Long> {

	public Optional<Project> findById(long id);

	public boolean existsByProjectName(String projectName);
}
