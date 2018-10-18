package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.Program;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/programs")
public class ProgramsResource {

	@POST
	public void addProgram(@FormParam("programName") String programName, @FormParam("programCode") String programCode) {

		Program programObject = new Program(programName, programCode);
		InMemoryDataStore.addProgram(programObject);
	}

	@GET
	@Path("/{programCode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProgram(@PathParam("programCode") String programCode) {

		return InMemoryDataStore.getProgram(programCode).toString();
	}
}
