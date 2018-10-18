package com.csye6225.fall2018.courseservice;

public class Announcement {

	private String announcementId;
	private String context;

	public Announcement(String announcementId, String context) {
		super();
		this.announcementId = announcementId;
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}

	@Override
	public String toString() {
		return "Announcement [announcementId=" + announcementId + ", context=" + context + "]";
	}

}
