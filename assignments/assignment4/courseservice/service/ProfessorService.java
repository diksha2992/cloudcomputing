package com.csye6225.fall2018.courseservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Professor;

public class ProfessorService {
	static com.csye6225.fall2018.courseservice.datamodel.entity.Professor prof_Map;
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public ProfessorService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
	}

	public Professor addProfessor(Professor professor) {
		String professorId = UUID.randomUUID().toString();
		Long joiningDate = new Date().getTime();
		professor.setProfessorId(professorId);
		professor.setJoiningDate(joiningDate);
		mapper.save(professor);
		return professor;
	}

	public Professor getProfessorByProfessorId(String professorid) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(professorid));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("ProfessorId-index").withConsistentRead(false)
				.withKeyConditionExpression("ProfessorId = :v1").withExpressionAttributeValues(eav);

		List<Professor> iList = mapper.query(Professor.class, queryExpression);
		Professor professor = iList.size() > 0 ? iList.get(0) : null;
		return professor;
	}
}
