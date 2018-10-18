package com.csye6225.fall2018.courseservice.datamodel;

import java.util.HashMap;
import java.util.Map;

import com.csye6225.fall2018.courseservice.Board;
import com.csye6225.fall2018.courseservice.Course;
import com.csye6225.fall2018.courseservice.Professor;
import com.csye6225.fall2018.courseservice.Program;
import com.csye6225.fall2018.courseservice.Student;

public class InMemoryDataStore {

	private static Map<String, Program> PROGRAM_MAP = new HashMap<>();
	private static Map<String, Student> STUDENTS_MAP = new HashMap<>();
	private static Map<String, Course> COURSE_MAP = new HashMap<>();
	private static Map<String, Professor> PROFESSOR_MAP = new HashMap<>();
	private static Map<String, Board> BOARD_MAP = new HashMap<>();

	public static Map<String, Board> getBOARD_MAP() {
		return BOARD_MAP;
	}

	public static void setBOARD_MAP(Map<String, Board> bOARD_MAP) {
		BOARD_MAP = bOARD_MAP;
	}

	public static void addProgram(Program program) {
		PROGRAM_MAP.put(program.getProgramCode(), program);
	}

	public static void addProfessor(Professor professor) {
		PROFESSOR_MAP.put(professor.getProfessorID(), professor);
	}

	public static Professor getProfessor(String professorID) {
		return PROFESSOR_MAP.get(professorID);
	}

	public static void addCourse(Course course) {
		COURSE_MAP.put(course.getCourseID(), course);
	}

	public static Course getCourse(String courseId) {
		return COURSE_MAP.get(courseId);
	}

	public static boolean doesProgramExist(String programCode) {
		Program program = PROGRAM_MAP.get(programCode);
		if (program == null) {
			return false;
		}
		return true;
	}

	public static Program getProgram(String programCode) {
		return PROGRAM_MAP.get(programCode);
	}

	public static void addStudent(Student student) {
		STUDENTS_MAP.put(student.getStudentID(), student);
	}

	public static Student getStudent(String studentID) {
		Student student = STUDENTS_MAP.get(studentID);
		return student;

	}

}
