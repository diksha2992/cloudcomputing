package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Announcement;
import com.csye6225.fall2018.courseservice.datamodel.entity.Board;
import com.csye6225.fall2018.courseservice.datamodel.entity.Course;

public class BoardService {
	DynamoDBMapper mapper;
	private CourseService courseService;
	private AnnouncementService announcementService;

	public BoardService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
		courseService = new CourseService();
		announcementService = new AnnouncementService();
	}

	public Board getBoardByBoardId(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(boardId));

		DynamoDBQueryExpression<Board> queryExpression = new DynamoDBQueryExpression<Board>()
				.withIndexName("boardId-index").withConsistentRead(false).withKeyConditionExpression("boardId = :v1")
				.withExpressionAttributeValues(eav);

		List<Board> iList = mapper.query(Board.class, queryExpression);
		Board board = iList.size() > 0 ? iList.get(0) : null;
		return board;
	}

	public Announcement postAnnouncement(String announcementText, String courseId) {

		Course course = courseService.getCourseByCourseId(courseId);

		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}

		String boardId = course.getBoardId();
		Announcement announcement = new Announcement(announcementText, boardId);
		announcementService.postAnnouncement(announcement);

		return announcement;
	}

	public List<Announcement> getAnnouncements(String courseId) {

		Course course = courseService.getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}

		String boardId = course.getBoardId();

		List<Announcement> announcements = announcementService.getAnnouncements(boardId);
		return announcements;
	}

	public void deleteAnnouncement(String announcementId, String courseId) {

		Course course = courseService.getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}

		String boardId = course.getBoardId();

		announcementService.deleteAnnouncement(announcementId, boardId);
	}

	public Announcement updateAnnouncement(String announcementId, String announcementText, String courseId) {
		Course course = courseService.getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}

		String boardId = course.getBoardId();

		return announcementService.updateAnnouncement(announcementId, announcementText, boardId);
	}
}
