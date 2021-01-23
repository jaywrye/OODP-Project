package cz2002assignment;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student_Course {
	private String studentName;
	private String matNo;
	private String CourseCode;
	private int indexNum;
	private boolean isWait;
	private LocalDateTime qDatetime; 
	int AU;
	
	public Student_Course(String studentName, String matNo, String CourseCode, int indexNum, boolean isWait, LocalDateTime qDatetime,int AU) {
		this.studentName= studentName;
		this.matNo= matNo;
		this.CourseCode= CourseCode;
		this.indexNum= indexNum;
		this.isWait= isWait;
		this.qDatetime= qDatetime;
		this.AU=AU;
	}
	public Student_Course(String studentName, String matNo, String CourseCode, int indexNum,int AU) {
		this.studentName= studentName;
		this.matNo= matNo;
		this.CourseCode= CourseCode;
		this.indexNum= indexNum;
		this.isWait= false;
		this.AU=AU;
	}

	public void setStudentName(String studentName) {
		this.studentName= studentName;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setMatNo(String matNo) {
		this.matNo= matNo;
	}
	
	public String getMatNo() {
		return matNo;
	}
	
	public void setCourseCode(String CourseCode) {
		this.CourseCode= CourseCode;

	}
	
	public String getCourseCode() {
		return CourseCode;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum= indexNum;
	}
	
	public int getIndexNum() {
		return indexNum;
	}
	
	public void setIsWait(boolean isWait) {
		this.isWait= isWait;
	}
	
	public boolean getIsWait() {
		return isWait;
	}
	
	public void setQdateTime(LocalDateTime qDateTime) {
		this.qDatetime= qDatetime;
	}
	
	public LocalDateTime getQdateTime() {
		return qDatetime;
	}
	public int getAU() {
		return AU;
	}
	public void setAU(int aU) {
		AU = aU;
	}
	
	
}
