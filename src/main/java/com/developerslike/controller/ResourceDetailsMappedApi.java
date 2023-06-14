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

import com.developerslike.data.ResourceMappedData;
import com.developerslike.service.IResourceMappedService;

@CrossOrigin
@RestController
public class ResourceDetailsMappedApi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	IResourceMappedService resourceMappedService;

	@PostMapping(value = "/saveResourceMapped", consumes = "application/json")
	public String saveResourceMapped(@RequestBody ResourceMappedData resourceData) {
		return resourceMappedService.saveResourceMapped(resourceData);
	}

	@PostMapping(value = "/updateResourceMapped", consumes = "application/json")
	public String updateResourceMapped(@RequestBody ResourceMappedData resourceData) {
		return resourceMappedService.updateResourceMapped(resourceData);
	}

	@DeleteMapping(value = "/deleteResourceMappedById/{id}")
	public String deleteResourceMappedById(@PathVariable(required = true) long id) {
		return resourceMappedService.deleteResourceMappedById(id);
	}

	@GetMapping(value = "/getAllResourceMapped", produces = "application/json")
	public List<ResourceMappedData> getAllResourceMapped() {
		return resourceMappedService.getAllResourceMapped();

	}

}
