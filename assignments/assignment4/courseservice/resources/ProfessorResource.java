package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.entity.Professor;
import com.csye6225.fall2018.courseservice.resources.requestentity.ProfessorRequest;
import com.csye6225.fall2018.courseservice.service.ProfessorService;

@Path("/professor")
public class ProfessorResource {

	private ProfessorService professorService;

	public ProfessorResource() {
		professorService = new ProfessorService();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professor addProfessor(ProfessorRequest professorRequest) {

		if (professorRequest == null || professorRequest.getFirstName() == null
				|| professorRequest.getLastName() == null || professorRequest.getDepartment() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + professorRequest);
		}
		String firstName = professorRequest.getFirstName();
		String lastName = professorRequest.getLastName();
		String department = professorRequest.getDepartment();
		Professor professor = new Professor(firstName, lastName, department);

		professorService.addProfessor(professor);
		return professor;
	}

	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String professorId) {

		Professor professor = professorService.getProfessorByProfessorId(professorId);
		System.out.println("Returning professor:" + professor);
		return professor;
	}

}
