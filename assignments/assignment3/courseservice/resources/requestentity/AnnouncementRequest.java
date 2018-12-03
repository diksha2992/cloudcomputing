package com.csye6225.fall2018.courseservice.resources.requestentity;

public class AnnouncementRequest {

	private String courseId;
	private String announcementText;

	public AnnouncementRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getAnnouncementText() {
		return announcementText;
	}

	public void setAnnouncementText(String announcementText) {
		this.announcementText = announcementText;
	}

	@Override
	public String toString() {
		return "AnnouncementRequest [courseId=" + courseId + ", announcementText=" + announcementText + "]";
	}

}
