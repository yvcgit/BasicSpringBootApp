package com.developerslike;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.developerslike.dao.IResourceDao;
import com.developerslike.data.ResourceData;
import com.developerslike.entity.Resource;
import com.developerslike.service.impl.ResourceService;

public class ResourceServiceTest extends ApplicationTestRunner {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	IResourceDao mockResourceDao;
	@InjectMocks
	ResourceService injectMockResourceService;
	/*
	 * private static ResourceService resourceSerice;
	 * 
	 * @BeforeClass public static void beforeClass() { resourceSerice = new
	 * ResourceService(); }
	 */

	@Test
	public void saveResourceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		ResourceData resourceData = getResourceData();

		Method method = ResourceService.class.getDeclaredMethod("toEntity", ResourceData.class);
		method.setAccessible(true);
		Resource resource = (Resource) method.invoke(injectMockResourceService, resourceData);

		Mockito.when(mockResourceDao.save(Mockito.any(Resource.class))).thenReturn(resource);
		injectMockResourceService.saveResource(resourceData);
		
		assertThat("", resourceData.getResourceName().equals(resource.getResourceName()));
		Mockito.verify(mockResourceDao).save(Mockito.any(Resource.class));

	}

	@Test
	//@Ignore
	public void saveResourceTest_1() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
//		ResourceData resourceData = getResourceData();
//		Resource resource = getResource();
//		ResourceService spycs = spy(new ResourceService());
		//doReturn(resource).when(spycs , "toEntity");
		// Mockito.when(mockResourceDao.save(mockResource)).thenReturn(mockResource);
		// Mockito.when(resourceSerice.toEntity(resourceData)).thenReturn(resource);

		// injectMockResourceService.saveResource(mockResourceData);
		// assertThat("",resourceData.getContactNumber().equals(resource.getContactNumber()));
		// Mockito.verify(resourceDao).save(resource);
		// Mockito.verify(resourceDao.save(Mockito.any(Resource.class)),
		// Mockito.atLeastOnce());
		//Mockito.verify(mockResourceDao.save(Mockito.any(Resource.class)), Mockito.times(1));

	}

	/**
	 * Method method = targetClass.getDeclaredMethod("method", String.class);
	 * method.setAccessible(true); return (String)method.invoke(targetObject,
	 * "mystring");
	 */

	@Test
	public void updateResourceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		ResourceData resourceData = getResourceData();
		Resource resource = getResource();

		Method method = ResourceService.class.getDeclaredMethod("update", Resource.class, ResourceData.class);
		method.setAccessible(true);
		Resource updateResource = (Resource) method.invoke(injectMockResourceService, resource, resourceData);

		Optional<Resource> optionalResource = Optional.of(resource);
		Mockito.when(mockResourceDao.findById(Mockito.anyLong())).thenReturn(optionalResource);
		Mockito.when(mockResourceDao.save(resource)).thenReturn(optionalResource.get());
		injectMockResourceService.updateResource(resourceData);
		
		assertThat("", resourceData.getResourceName().equals(updateResource.getResourceName()));
		Mockito.verify(mockResourceDao, times(1)).save(Mockito.any(Resource.class));
		// Mockito.verify(mockResourceDao).save(Mockito.any(Resource.class),Mockito.times(1);
		Mockito.verify(mockResourceDao, atLeast(1)).save(Mockito.any(Resource.class));
		Mockito.verify(mockResourceDao, atLeast(1)).findById(Mockito.anyLong());


	}

	@Test
	public void getResourceByIdTest() {

		Resource resource = getResource();
		Optional<Resource> optionalResource = Optional.of(resource);

		Mockito.when(mockResourceDao.findById(Mockito.anyLong())).thenReturn(optionalResource);
		injectMockResourceService.getResourceById(resource.getId());

		Mockito.verify(mockResourceDao, times(1)).findById(Mockito.anyLong());

	}
	@Test
	public void deleteResourceByIdTest() {
		
		doNothing().when(mockResourceDao).deleteById(Mockito.anyLong());
		injectMockResourceService.deleteResourceById(Mockito.anyLong());

		Mockito.verify(mockResourceDao, times(1)).deleteById(Mockito.anyLong());

	}
	
	@Test
	public void getResourceListTest() {

		Iterable<Resource> resourceDataList = new ArrayList<Resource>();
		
		Mockito.when(mockResourceDao.findAll()).thenReturn(resourceDataList);
		
		injectMockResourceService.getResourceList();
		
		
		Mockito.verify(mockResourceDao, times(1)).findAll();

	}
	private ResourceData getResourceData() {
		ResourceData resourceData = new ResourceData();
		resourceData.setId(1);
		resourceData.setResourceId("vy57431");
		resourceData.setResourceName("venkat");
		return resourceData;
	}

	private Resource getResource() {
		Resource resource = new Resource();
		resource.setResourceId("vy57431");
		resource.setResourceName("venkat");
		
		return resource;
	}
}
