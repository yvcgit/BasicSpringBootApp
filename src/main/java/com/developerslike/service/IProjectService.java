package com.developerslike.service;

import java.io.Serializable;
import java.util.List;

import com.developerslike.data.ProjectData;


public interface IProjectService extends Serializable {

	public void saveProject(ProjectData projectData);

	public ProjectData getProjectById(long id);

	public String updateProject(ProjectData projectData);

	public String deleteProjectById(long projectId);

	public List<ProjectData> getProjectList();

}
