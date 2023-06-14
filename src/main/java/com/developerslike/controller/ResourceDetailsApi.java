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

import com.developerslike.data.ResourceData;
import com.developerslike.service.IResourceService;

@CrossOrigin
@RestController
public class ResourceDetailsApi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	IResourceService resourceService;

	@PostMapping(value = "/saveResource", consumes = "application/json")
	public boolean saveResource(@RequestBody ResourceData resourceData) {
		resourceService.saveResource(resourceData);
		return true;
	}

	@GetMapping(value = "/getResourcebyId/{resourceId}", produces = "application/json")
	public ResourceData getResourcebyId(@PathVariable(required = true) long resourceId) {
		return resourceService.getResourceById(resourceId);

	}

	@PostMapping(value = "/updateResource", consumes = "application/json")
	public String updateResource(@RequestBody ResourceData resourceData) {
		return resourceService.updateResource(resourceData);
	}

	@DeleteMapping(value = "/deleteResourceById/{resourceId}")
	public String deleteResourceById(@PathVariable(required = true) long resourceId) {
		return resourceService.deleteResourceById(resourceId);
	}

	@GetMapping(value = "/getAllResources", produces = "application/json")
	public List<ResourceData> getAllResources() {
		return resourceService.getResourceList();

	}

}
