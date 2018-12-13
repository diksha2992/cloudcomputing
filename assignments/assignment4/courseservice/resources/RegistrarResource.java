package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.entity.Registrar;
import com.csye6225.fall2018.courseservice.resources.requestentity.AddRegistrarRequest;
import com.csye6225.fall2018.courseservice.service.RegistrarService;

@Path("/registerOffering")
public class RegistrarResource {

	private RegistrarService registrarService;

	public RegistrarResource() {
		registrarService = new RegistrarService();
	}

	// Add Registrar
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Registrar addRegistrar(AddRegistrarRequest addRegistrarRequest) {

		if (addRegistrarRequest == null || addRegistrarRequest.getCourseId() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + addRegistrarRequest);
		}
		String courseId = addRegistrarRequest.getCourseId();

		Registrar registrar = registrarService.addRegistrarForCourse(courseId);
		return registrar;
	}
}
