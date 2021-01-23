package cz2002assignment;

public class Student {
    private String matNo;
	private String name;
	private String emailAdd;
	private char gender;
	private String school;
	private int studyYear;
	private String program;
	private String nationality;

    public Student(String matNo, String name, String emailAdd, char gender, String school, int studyYear, String program, String nationality){
		this.matNo = matNo;
		this.name = name;
		this.emailAdd = emailAdd;
		this.gender = gender;
		this.school = school;
		this.program = program;
		this.studyYear = studyYear;
		this.nationality = nationality;
	}

	public String getMatNo() {
		return matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

    
}
