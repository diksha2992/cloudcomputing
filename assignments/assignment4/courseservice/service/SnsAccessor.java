package com.csye6225.fall2018.courseservice.service;

import java.util.UUID;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class SnsAccessor {

	static AmazonSNS amazonSns;

	public static void init() {
		if (amazonSns == null) {
			AWSCredentialsProvider credentialsProvider = new AWSCredentialsProviderChain(
					new InstanceProfileCredentialsProvider(false), new EnvironmentVariableCredentialsProvider(),
					new ProfileCredentialsProvider("default"));
			amazonSns = AmazonSNSClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-1")
					.build();
		}
	}

	public SnsAccessor() {
		init();
	}

	public String createSnsTopic(String courseId) {
		String randomString = UUID.randomUUID().toString();
		CreateTopicRequest createTopicRequest = new CreateTopicRequest("sns-topic-" + courseId + "-" + randomString);
		CreateTopicResult createTopicResult = amazonSns.createTopic(createTopicRequest);
		String topicArn = createTopicResult.getTopicArn();
		System.out.println("Created SNS with Arn: " + topicArn);
		return topicArn;
	}

	public void subscribe(String snsTopic, String emailId) {
		SubscribeRequest subRequest = new SubscribeRequest(snsTopic, "email", emailId);
		amazonSns.subscribe(subRequest);
	}

	public void publish(String snsTopic, String message) {
		PublishRequest publishRequest = new PublishRequest(snsTopic, message);
		amazonSns.publish(publishRequest);
	}
}
