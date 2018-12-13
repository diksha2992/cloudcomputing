package com.csye6225.fall2018.courseservice.lambda;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String getCourseIdFromJson(String jsonString) {
		try {
			WorkflowInput workflowInput = mapper.readValue(jsonString, WorkflowInput.class);

			String courseId = workflowInput.getCourseId();
			if (courseId == null || courseId.isEmpty()) {
				throw new RuntimeException("No courseId found in input");
			}
			return courseId;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
