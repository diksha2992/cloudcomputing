package com.csye6225.fall2018.courseservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice.service.CourseService;

public class BoardCreationLambda implements RequestHandler<WorkflowInput, WorkflowInput> {

	private CourseService courseService;

	public BoardCreationLambda() {
		this.courseService = new CourseService();
	}

	@Override
	public WorkflowInput handleRequest(WorkflowInput input, Context context) {

		String courseId = input.getCourseId();

		courseService.addBoardForCourse(courseId);

		return input;
	}
}
