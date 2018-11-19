package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Announcement;

public class AnnouncementService {
	DynamoDBMapper mapper;

	public AnnouncementService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
	}

	public Announcement postAnnouncement(Announcement announcement) {

		String announcementId = UUID.randomUUID().toString();
		announcement.setAnnouncementId(announcementId);
		mapper.save(announcement);

		return announcement;
	}

	public Announcement updateAnnouncement(String announcementId, String announcementText, String boardId) {

		Announcement announcement = getAnnouncementById(announcementId, boardId);
		if (announcement == null) {
			throw new IllegalArgumentException("Announcement with Id: " + announcementId + " doesn't exists.");
		}
		announcement.setAnnouncementText(announcementText);
		mapper.save(announcement);
		return announcement;
	}

	public List<Announcement> getAnnouncements(String boardId) {

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("BoardId-AnnouncementId-index").withConsistentRead(false)
				.withKeyConditionExpression("BoardId = :v1").withExpressionAttributeValues(eav);

		List<Announcement> announcements = mapper.query(Announcement.class, queryExpression);
		return announcements;
	}

	public Announcement getAnnouncementById(String announcementId, String boardId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));
		eav.put(":v2", new AttributeValue().withS(announcementId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
				.withIndexName("BoardId-AnnouncementId-index").withConsistentRead(false)
				.withKeyConditionExpression("BoardId = :v1 and AnnouncementId = :v2")
				.withExpressionAttributeValues(eav);

		List<Announcement> announcements = mapper.query(Announcement.class, queryExpression);
		return announcements != null && announcements.size() > 0 ? announcements.get(0) : null;
	}

	public void deleteAnnouncement(String announcementId, String boardId) {

		Announcement announcement = getAnnouncementById(announcementId, boardId);
		if (announcement == null) {
			throw new IllegalArgumentException("Announcement with Id: " + announcementId + " doesn't exists.");
		}

		mapper.delete(announcement);
	}

}
