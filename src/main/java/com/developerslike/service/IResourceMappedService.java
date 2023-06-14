package com.developerslike.service;

import java.io.Serializable;
import java.util.List;

import com.developerslike.data.ResourceMappedData;


public interface IResourceMappedService extends Serializable {

	public String saveResourceMapped(ResourceMappedData resourceMappedData);

	public String updateResourceMapped(ResourceMappedData resourceMappedData);

	public String deleteResourceMappedById(long id);

	public List<ResourceMappedData> getAllResourceMapped();

}
