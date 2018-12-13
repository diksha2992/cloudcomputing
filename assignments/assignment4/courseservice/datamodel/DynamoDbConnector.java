package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {

	static AmazonDynamoDB dynamoDB;

	public static void init() {
		if (dynamoDB == null) {
			AWSCredentialsProvider credentialsProvider = new AWSCredentialsProviderChain(
					new InstanceProfileCredentialsProvider(false), new EnvironmentVariableCredentialsProvider(),
					new ProfileCredentialsProvider("default"));
			dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
					.withRegion("us-east-1").build();
		}
	}

	public static AmazonDynamoDB getClient() {
		init();
		return dynamoDB;
	}
}