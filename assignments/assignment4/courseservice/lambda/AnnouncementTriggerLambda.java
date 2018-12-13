package com.csye6225.fall2018.courseservice.lambda;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.csye6225.fall2018.courseservice.datamodel.entity.Board;
import com.csye6225.fall2018.courseservice.datamodel.entity.Course;
import com.csye6225.fall2018.courseservice.service.BoardService;
import com.csye6225.fall2018.courseservice.service.CourseService;
import com.csye6225.fall2018.courseservice.service.SnsAccessor;

public class AnnouncementTriggerLambda implements RequestHandler<DynamodbEvent, Void> {

	private BoardService boardService;
	private CourseService courseService;
	private SnsAccessor snsAccessor;

	public AnnouncementTriggerLambda() {
		this.boardService = new BoardService();
		this.courseService = new CourseService();
		this.snsAccessor = new SnsAccessor();
	}

	@Override
	public Void handleRequest(DynamodbEvent dynamodbEvent, Context context) {

		for (DynamodbStreamRecord record : dynamodbEvent.getRecords()) {
			System.out.println("Record: " + record);
			Map<String, AttributeValue> image = record.getDynamodb().getNewImage();

			if (image == null) {
				System.out.println("No new image");
				return null;
			}
			System.out.println("New Image..");
			for (Map.Entry<String, AttributeValue> e : image.entrySet()) {
				System.out.println(e.getKey() + ": " + e.getValue());
			}
			String boardId = image.get("BoardId").getS();
			String announcementText = image.get("AnnouncementText").getS();

			Board board = boardService.getBoardByBoardId(boardId);
			String courseId = board.getCourseId();

			System.out.println("CourseId: " + courseId);

			Course course = courseService.getCourseById(courseId);

			String notificationTopic = course.getNotificationTopic();

			System.out.println("Notification Topic: " + notificationTopic);

			System.out.println("Publishing to topic..");

			snsAccessor.publish(notificationTopic, announcementText);
		}

		return null;
	}
}
