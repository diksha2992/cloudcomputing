package com.csye6225.fall2018.courseservice.resources;

import java.util.Random;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.Professor;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/professor")
public class ProfessorResource {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addProfessor(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("department") String department) {

		String professorId = String.valueOf(new Random().nextInt());
		Professor professor = new Professor(firstName, lastName, department, professorId);

		InMemoryDataStore.addProfessor(professor);
		System.out.println("Inserted " + professorId);
		return professor.getProfessorID();
	}

	@GET
	@Path("/{professorId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProfessor(@PathParam("professorId") String professorId) {

		System.out.println("Received request: " + professorId);
		Professor professor = InMemoryDataStore.getProfessor(professorId);
		System.out.println("Returning professor:" + professor);
		return professor == null ? "" : professor.toString();
	}
}
