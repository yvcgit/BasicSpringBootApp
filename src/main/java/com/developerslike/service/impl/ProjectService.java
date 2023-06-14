package com.developerslike.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.developerslike.dao.IProjectDao;
import com.developerslike.data.ProjectData;
import com.developerslike.entity.Project;
import com.developerslike.service.IProjectService;

@Service
public class ProjectService implements IProjectService {

	private static final long serialVersionUID = 1L;

	@Autowired
	IProjectDao projectDao;

	@Override
	public void saveProject(ProjectData projectData) {
		if (!checkIsProjectExisting(projectData.getProjectName())) {
			projectDao.save(toEntity(projectData));
		}
	}

	private boolean checkIsProjectExisting(String projectName) {
		return projectDao.existsByProjectName(projectName);
	}

	@Override
	public ProjectData getProjectById(long id) {
		Optional<Project> findById = projectDao.findById(id);
		if (!findById.isPresent())
			throw new DataAccessResourceFailureException("no result=====");
		return toData(findById.get());
	}

	private ProjectData toData(Project project) {
		ProjectData projectData = new ProjectData();
		projectData.setId(project.getId());
		projectData.setProjectName(project.getProjectName());
		return projectData;
	}

	@Override
	public String updateProject(ProjectData projectData) {

		Optional<Project> findByResourceId = projectDao.findById(projectData.getId());
		if (findByResourceId.isPresent()) {
			String name = findByResourceId.get().getProjectName();
			if (!name.equalsIgnoreCase(projectData.getProjectName())) {
				if (!checkIsProjectExisting(projectData.getProjectName())) {
					projectDao.save(update(findByResourceId.get(), projectData));
					return "update " + name + " to " + projectData.getProjectName();
				} else {
					return "project " + projectData.getProjectName() + " alredy existed";
				}
			} else {
				return "updated project " + name + " same as existing name";
			}
		}

		return "no record to update";

	}

	private Project update(Project project, ProjectData projectData) {
		project.setProjectName(
				projectData.getProjectName() != null ? projectData.getProjectName() : project.getProjectName());
		return project;

	}

	@Override
	public String deleteProjectById(long projectId) {
		try {
			projectDao.deleteById(Long.valueOf(projectId));
			return "Deleted";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<ProjectData> getProjectList() {
		List<ProjectData> projectDataList = new ArrayList<>();
		Iterable<Project> findAll = projectDao.findAll();
		findAll.forEach(c -> projectDataList.add(toData(c)));

		return projectDataList;
	}

	public Project toEntity(ProjectData projectData) {
		Project project = null;
		if (projectData.getId() > 0) {
			Optional<Project> findByprojectId = projectDao.findById(projectData.getId());
			if (findByprojectId.isPresent()) {
				project = findByprojectId.get();
			}
		} else {
			project = new Project();
			project.setProjectName(projectData.getProjectName());
		}
		return project;
	}

	public List<Project> toEntityList(List<ProjectData> projectsData) {
		List<Project> projects = new ArrayList<Project>();
		System.out.println("projectsdata ========================  " + projectsData.size());
		projectsData.forEach(a -> {
			System.out.println("project ========================  " + a);
			projects.add(toEntity(a));
		});
		return projects;
	}

}
