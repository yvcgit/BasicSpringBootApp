package com.developerslike.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.developerslike.dao.IProjectDao;
import com.developerslike.dao.IResourceDao;
import com.developerslike.data.ResourceData;
import com.developerslike.entity.Resource;
import com.developerslike.service.IResourceService;

@Service
public class ResourceService implements IResourceService {

	private static final long serialVersionUID = 1L;

	@Autowired
	IResourceDao resourceDao;

	@Autowired
	IProjectDao projectDao;

	@Override
	public void saveResource(ResourceData resourceData) {
		if(!checkIsResourceIdExisted(resourceData.getResourceId())) {
		resourceDao.save(toEntity(resourceData));
		}
	}

	private boolean checkIsResourceIdExisted(String resourceId) {
		return resourceDao.existsByResourceId(resourceId);
	}
	private boolean checkIsResourceNameExisted(String resourceName) {
		return resourceDao.existsByResourceName(resourceName);
	}
	private Resource toEntity(ResourceData resourceData) {
		Resource resource = new Resource();
		resource.setResourceId(resourceData.getResourceId());
		resource.setResourceName(resourceData.getResourceName());

		return resource;
	}

	@Override
	public String updateResource(ResourceData resourceData) {
		Optional<Resource> findByResourceId = resourceDao.findById(resourceData.getId());
		if (findByResourceId.isPresent()) {
			String name = findByResourceId.get().getResourceName();
			if(!name.equalsIgnoreCase(resourceData.getResourceName())) {
				if(!checkIsResourceNameExisted(resourceData.getResourceName())) {
			resourceDao.save(update(findByResourceId.get(), resourceData));
			return "update " + name + " to " + resourceData.getResourceName();
				} else {
					return "resource name "+resourceData.getResourceName() +" already existed";
				}
			} else {
				return "updated resource name "+name+" same as existing name";
			}
		}

		return "no record to update";
	}

	private Resource update(Resource resource, ResourceData resourceData) {
		resource.setResourceName(
				resourceData.getResourceName() != null ? resourceData.getResourceName() : resource.getResourceName());

		return resource;
	}

	@Override
	public ResourceData getResourceById(long id) {
		Optional<Resource> findById = resourceDao.findById(id);
		if (!findById.isPresent())
			throw new DataAccessResourceFailureException("no result=====");
		return toData(findById.get());
	}

	private ResourceData toData(Resource resource) {
		ResourceData resourceData = new ResourceData();
		resourceData.setId(resource.getId());
		resourceData.setResourceId(resource.getResourceId());
		resourceData.setResourceName(resource.getResourceName());

		return resourceData;
	}

	@Override

	public String deleteResourceById(long resourceId) {
		try {
			resourceDao.deleteById(Long.valueOf(resourceId));
			return "Deleted";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public List<ResourceData> getResourceList() {

		List<ResourceData> resourceDataList = new ArrayList<>();
		Iterable<Resource> findAll = resourceDao.findAll();
		findAll.forEach(c -> resourceDataList.add(toData(c)));

		return resourceDataList;
	}

}
