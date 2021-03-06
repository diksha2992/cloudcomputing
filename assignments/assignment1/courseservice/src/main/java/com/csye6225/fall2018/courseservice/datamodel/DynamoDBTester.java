package com.csye6225.fall2018.courseservice.datamodel;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public class DynamoDBTester {
	static AmazonDynamoDB dynamoDB;

	private static void init() throws Exception {
		AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		credentialsProvider.getCredentials();

		dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-1")
				.build();

	}

	public static void main(String[] args) throws Exception {
		init();
		String tablename = "students-test";
		GetItemRequest getItemRequest = new GetItemRequest();

		Map<String, AttributeValue> itemToFetch = new HashMap<>();
		itemToFetch.put("studentId", new AttributeValue().withS("123"));
		getItemRequest.setKey(itemToFetch);

		getItemRequest.setTableName("students-test");
		GetItemResult getItemResult = dynamoDB.getItem(getItemRequest);
		System.out.println("GetItemResult:" + getItemResult);
	}
}
