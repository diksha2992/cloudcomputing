package com.csye6225.fall2018.courseservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private String courseID;
	private Map<String, Announcement> announcementsMap;

	public Board(String courseID) {
		this.courseID = courseID;
		this.announcementsMap = new HashMap<>();
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public List<Announcement> getAnnouncements() {
		return new ArrayList<>(announcementsMap.values());
	}

	public Announcement getAnnouncement(String announcementId) {
		return announcementsMap.get(announcementId);
	}

	public void addAnnouncement(Announcement announcement) {
		this.announcementsMap.put(announcement.getAnnouncementId(), announcement);
	}

	public void removeAnnouncement(String announcementId) {
		this.announcementsMap.remove(announcementId);
	}

	@Override
	public String toString() {
		return "Board [courseID=" + courseID + ", announcementsMap=" + announcementsMap + "]";
	}

}
