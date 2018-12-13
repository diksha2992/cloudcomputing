package com.csye6225.fall2018.courseservice.lambda;

import java.util.Map;
import java.util.UUID;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.OperationType;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CourseTriggerLambda implements RequestHandler<DynamodbEvent, Void> {

	static AWSStepFunctions stepFunctionsClient;

	public static void init() {
		if (stepFunctionsClient == null) {
			AWSCredentialsProvider credentialsProvider = new AWSCredentialsProviderChain(
					new InstanceProfileCredentialsProvider(false), new EnvironmentVariableCredentialsProvider(),
					new ProfileCredentialsProvider("default"));
			stepFunctionsClient = AWSStepFunctionsClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion("us-east-1").build();
		}
	}

	public CourseTriggerLambda() {
		init();
	}

	@Override
	public Void handleRequest(DynamodbEvent dynamodbEvent, Context context) {

		String courseId = "";
		boolean isNewCourse = false;

		for (DynamodbStreamRecord record : dynamodbEvent.getRecords()) {
			System.out.println("Record: " + record);

			String eventName = record.getEventName();
			Map<String, AttributeValue> image = record.getDynamodb().getNewImage();

			if (eventName.equals(OperationType.INSERT.name()) && image != null) {
				courseId = image.get("CourseId").getS();
				String department = image.get("Department").getS();
				AttributeValue boardIdAttribute = image.get("BoardId");

				if (boardIdAttribute == null && !department.equals("Seminars")) {
					isNewCourse = true;
				}
			}
			try {
				ObjectMapper mapper = new ObjectMapper();
				WorkflowInput input = new WorkflowInput(courseId, isNewCourse);
				String inputString = mapper.writeValueAsString(input);

				System.out.println("Starting stepfunctions with input:" + input);
				StartExecutionRequest startExecutionRequest = new StartExecutionRequest()
						.withStateMachineArn(
								"arn:aws:states:us-east-1:784533316345:stateMachine:CourseCreationStateMachine")
						.withName(courseId + "-" + UUID.randomUUID().toString()).withInput(inputString);
				stepFunctionsClient.startExecution(startExecutionRequest);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
