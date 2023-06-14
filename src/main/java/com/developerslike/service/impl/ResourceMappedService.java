package com.developerslike.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developerslike.dao.IProjectDao;
import com.developerslike.dao.IResourceDao;
import com.developerslike.dao.IResourceMappedDao;
import com.developerslike.data.ResourceMappedData;
import com.developerslike.entity.Project;
import com.developerslike.entity.Resource;
import com.developerslike.entity.ResourceMapped;
import com.developerslike.service.IResourceMappedService;

@Service
public class ResourceMappedService implements IResourceMappedService {

	private static final long serialVersionUID = 1L;

	@Autowired
	IResourceMappedDao resourceMappedDao;

	@Autowired
	IResourceDao resourceDao;

	@Autowired
	IProjectDao projectDao;

	@Override
	public String saveResourceMapped(ResourceMappedData resourceMappedData) {
		boolean existsByProjectIdAndResourceId = resourceMappedDao
				.existsByProjectIdAndResourceId(resourceMappedData.getProjectId(), resourceMappedData.getResourceId());
		System.out.println("existsByProjectIdAndResourceId  " + existsByProjectIdAndResourceId);
		if (!existsByProjectIdAndResourceId) {
			resourceMappedDao.save(toEntity(resourceMappedData));
		} else {
			return "Already Mapped User in this project";
		}
		return "Resource Mapped successfully";
	}

	private ResourceMapped toEntity(ResourceMappedData resourceMappedData) {
		ResourceMapped resourceMapped = new ResourceMapped();
		resourceMapped.setProjectId(resourceMappedData.getProjectId());
		resourceMapped.setResourceId(resourceMappedData.getResourceId());
		return resourceMapped;
	}

	@Override
	public String updateResourceMapped(ResourceMappedData resourceMappedData) {
		Optional<ResourceMapped> findByResourceMappedId = resourceMappedDao.findById(resourceMappedData.getId());
		if (findByResourceMappedId.isPresent()) {
			resourceMappedDao.save(update(findByResourceMappedId.get(), resourceMappedData));
			return "update  to ";
		}

		return "no record to update";
	}

	private ResourceMapped update(ResourceMapped resourceMappedById, ResourceMappedData resourceMappedData) {
		ResourceMapped resourceMapped = new ResourceMapped();
		resourceMapped.setProjectId(resourceMappedData.getProjectId() != null ? resourceMappedData.getProjectId()
				: resourceMappedById.getProjectId());
		resourceMapped.setResourceId(resourceMappedData.getResourceId() != null ? resourceMappedData.getResourceId()
				: resourceMappedById.getResourceId());
		return resourceMapped;
	}

	@Override

	public String deleteResourceMappedById(long id) {
		try {
			resourceMappedDao.deleteById(Long.valueOf(id));
			return "Deleted";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<ResourceMappedData> getAllResourceMapped() {

		List<ResourceMappedData> resourceMappedDataList = new ArrayList<>();
		Iterable<ResourceMapped> findAll = resourceMappedDao.findAll();

		findAll.forEach(c -> {
			Optional<Resource> findById = resourceDao.findById(Long.valueOf(c.getResourceId()));
			Optional<Project> findById2 = projectDao.findById(Long.valueOf(c.getProjectId()));
			if (findById.get() != null && findById2.get() != null) {
				resourceMappedDataList.add(toDataAll(c.getId(), findById.get(), findById2.get()));
			}

		});

		return resourceMappedDataList.stream().sorted(Comparator.comparing(ResourceMappedData::getId)).collect(Collectors.toList());
	}

	private ResourceMappedData toDataAll(long id, Resource resource, Project project) {
		ResourceMappedData resourceMappedData = new ResourceMappedData();
		resourceMappedData.setId(id);
		resourceMappedData.setProjectId(String.valueOf(project.getId()));
		resourceMappedData.setProjectName(project.getProjectName());
		resourceMappedData.setResourceId(String.valueOf(resource.getId()));
		resourceMappedData.setResourceName(resource.getResourceName());
		return resourceMappedData;
	}

}
