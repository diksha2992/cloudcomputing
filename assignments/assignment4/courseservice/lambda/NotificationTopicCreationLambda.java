package com.csye6225.fall2018.courseservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice.service.CourseService;
import com.csye6225.fall2018.courseservice.service.SnsAccessor;

public class NotificationTopicCreationLambda implements RequestHandler<WorkflowInput, Void> {

	private CourseService courseService;
	private SnsAccessor snsAccessor;

	public NotificationTopicCreationLambda() {
		this.courseService = new CourseService();
		this.snsAccessor = new SnsAccessor();
	}

	@Override
	public Void handleRequest(WorkflowInput input, Context context) {

		String courseId = input.getCourseId();

		String snsArn = snsAccessor.createSnsTopic(courseId);

		courseService.addSnsTopicForCourse(courseId, snsArn);

		return null;
	}
}
