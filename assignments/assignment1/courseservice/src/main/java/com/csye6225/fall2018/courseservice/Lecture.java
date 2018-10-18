package com.csye6225.fall2018.courseservice;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
	private String lectureId;
	private List<String> notes;
	private List<String> materials;

	public Lecture(String lectureId) {
		super();
		this.lectureId = lectureId;
		this.notes = new ArrayList<>();
		this.materials = new ArrayList<>();
	}

	public void addNotes(String notes) {
		this.notes.add(notes);
	}

	public void addMaterials(String material) {
		this.materials.add(material);
	}

	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	public List<String> getMaterials() {
		return materials;
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}

	@Override
	public String toString() {
		return "Lecture [lectureId=" + lectureId + ", notes=" + notes + ", materials=" + materials + "]";
	}

}
