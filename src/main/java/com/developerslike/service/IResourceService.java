package com.developerslike.service;

import java.io.Serializable;
import java.util.List;

import com.developerslike.data.ResourceData;


public interface IResourceService extends Serializable {

	public void saveResource(ResourceData resourceData);

	public ResourceData getResourceById(long id);

	public String updateResource(ResourceData resourceData);

	public String deleteResourceById(long resourceId);

	public List<ResourceData> getResourceList();

}
