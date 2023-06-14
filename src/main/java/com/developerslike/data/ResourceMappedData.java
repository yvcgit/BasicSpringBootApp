package com.developerslike.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceMappedData {
	private long id;
	private String resourceId;
	private String projectId;
	private String resourceName;
	private String projectName;

}
