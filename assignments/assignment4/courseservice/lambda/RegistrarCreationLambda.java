package com.csye6225.fall2018.courseservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice.resources.requestentity.AddRegistrarRequest;
import com.csye6225.fall2018.courseservice.service.RestServiceAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistrarCreationLambda implements RequestHandler<WorkflowInput, WorkflowInput> {

	private RestServiceAccessor restServiceAccessor;

	public RegistrarCreationLambda() {
		this.restServiceAccessor = new RestServiceAccessor();
	}

	@Override
	public WorkflowInput handleRequest(WorkflowInput input, Context context) {

		String courseId = input.getCourseId();

		AddRegistrarRequest addRegistrarRequest = new AddRegistrarRequest();
		addRegistrarRequest.setCourseId(courseId);

		ObjectMapper mapper = new ObjectMapper();
		try {
			String requestString = mapper.writeValueAsString(addRegistrarRequest);
			restServiceAccessor.callAPI("registerOffering", requestString);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

		return input;
	}
}
