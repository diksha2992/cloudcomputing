package com.csye6225.fall2018.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.entity.Announcement;
import com.csye6225.fall2018.courseservice.resources.requestentity.AnnouncementRequest;
import com.csye6225.fall2018.courseservice.service.BoardService;

@Path("/course/board")
public class BoardResource {

	private BoardService boardService;

	public BoardResource() {
		boardService = new BoardService();
	}

	// Post Announcements to Board
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement postAnnouncements(AnnouncementRequest announcementRequest) {

		if (announcementRequest == null || announcementRequest.getCourseId() == null
				|| announcementRequest.getAnnouncementText() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + announcementRequest);
		}
		String announcementText = announcementRequest.getAnnouncementText();
		String courseId = announcementRequest.getCourseId();
		Announcement announcement = boardService.postAnnouncement(announcementText, courseId);
		return announcement;
	}

	// Get Announcements from Board
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncements(@PathParam("courseId") String courseId) {
		if (courseId == null) {
			throw new IllegalArgumentException("Input variables not passed correctly.");
		}
		List<Announcement> announcements = boardService.getAnnouncements(courseId);
		return announcements;
	}

	// Delete Announcements on Board
	@DELETE
	@Path("/{courseId}/{announcementId}")
	public void deleteAnnouncements(@PathParam("courseId") String courseId,
			@PathParam("announcementId") String announcementId) {

		if (courseId == null || announcementId == null) {
			throw new IllegalArgumentException("Input variables not passed correctly.");
		}
		boardService.deleteAnnouncement(announcementId, courseId);
	}

	// Update Announcements on Board
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{courseId}/{announcementId}")
	public Announcement updateAnnouncements(@PathParam("courseId") String courseId,
			@PathParam("announcementId") String announcementId, AnnouncementRequest announcementRequest) {

		if (announcementRequest == null || announcementRequest.getAnnouncementText() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + announcementRequest);
		}
		String announcementText = announcementRequest.getAnnouncementText();
		Announcement announcement = boardService.updateAnnouncement(announcementId, announcementText, courseId);
		return announcement;
	}
}
