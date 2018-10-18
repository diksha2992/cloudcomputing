package com.csye6225.fall2018.courseservice;

public class Program {

	String programName;
	String programCode;

	public Program(String name, String code) {
		this.programName = name;
		this.programCode = code;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	@Override
	public String toString() {
		return "Program [programName=" + programName + ", programCode=" + programCode + "]";
	}

}
