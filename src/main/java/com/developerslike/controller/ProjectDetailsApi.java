package com.developerslike.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developerslike.data.ProjectData;
import com.developerslike.service.IProjectService;

@CrossOrigin
@RestController
public class ProjectDetailsApi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	IProjectService projectService;

	@PostMapping(value = "/saveProject", consumes = "application/json")
	public boolean saveProject(@RequestBody ProjectData projectData) {
		projectService.saveProject(projectData);
		return true;
	}

	@GetMapping(value = "/getProjectbyId/{projectId}", produces = "application/json")
	public ProjectData getProjectbyId(@PathVariable(required = true) long projectId) {
		return projectService.getProjectById(projectId);

	}

	@PostMapping(value = "/updateProject", consumes = "application/json")
	public String updateProject(@RequestBody ProjectData projectData) {
		return projectService.updateProject(projectData);
	}

	@DeleteMapping(value = "/deleteProjectById/{projectId}")
	public String deleteProjectById(@PathVariable(required = true) long projectId) {
		return projectService.deleteProjectById(projectId);
	}

	@GetMapping(value = "/getAllProjects", produces = "application/json")
	public List<ProjectData> getAllProjects() {
		return projectService.getProjectList();

	}

}
