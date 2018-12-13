package com.csye6225.fall2018.courseservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestServiceAccessor {

	private static String WEB_URL = "http://cloudcomputeservice-env.ew3bhn6twj.us-east-1.elasticbeanstalk.com/webapi";

	public void callAPI(String apiPath, String request) throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost(WEB_URL + "/" + apiPath);

		StringEntity input = new StringEntity(request);
		input.setContentType("application/json");

		post.setEntity(input);

		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
	}
}
