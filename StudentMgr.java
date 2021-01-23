package cz2002assignment;

import java.io.*;
import java.util.ArrayList;

public class StudentMgr{
    private ArrayList<Student> studentList = new ArrayList<Student>();
    
    
    public ArrayList<String> addStudent(String matNo, String name, String emailAdd, char gender, String school, int studyYear, String program, String nationality){
    	Student adding = new Student(matNo, name, emailAdd, gender, school, studyYear, program, nationality);
    	studentList.add(adding);
    	String[] info ={matNo,name,emailAdd, Character.toString(gender), 
    			school,Integer.toString(studyYear),program,nationality};
		
		ArrayList<String> newStudent = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			newStudent.add(info[i]);
		}
		return newStudent;
    }
    
    public void removeStudent(String matNo) {
    	for(int i=0; i<studentList.size();i++) {
    		if(matNo.equals(studentList.get(i).getMatNo())) {
    			studentList.remove(i);
    			return;
    		}
    	}
    }
    
    public void editStudent() {
    	
    }
    
    public ArrayList<String> findStudentwithList(ArrayList<String> listofStud){
    	System.out.println("Student Name"+"\t"+ "Gender" +"\t"+ "Nationality");
    	for(int i=0;i<listofStud.size();i++) {
    		for(int j=0;j<studentList.size();j++) {
    			if(listofStud.get(i).equals(studentList.get(j).getMatNo())){
    				System.out.println(studentList.get(j).getName()+"\t"+studentList.get(j).getGender()+"\t"+studentList.get(j).getNationality());
    			}
    		}
    	}
    return listofStud;
    }
    public String findStudentwithMat(String studMatNo){
    		for(int j=0;j<studentList.size();j++) {
    			if(studMatNo.equals(studentList.get(j).getMatNo())){
    				return studentList.get(j).getName();
    			}
    		}
    return "???";
    }
    
    public boolean isMatTaken(String MatNo) {
    	for (int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getMatNo().equals(MatNo)) {
				return true;
			}
		}
    	return false;
    }

    public String getEmailByMatNo(String MatNo) {
    	for (int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getMatNo().equals(MatNo)) {
				return studentList.get(i).getEmailAdd();
			}
		}
    	return null;
    }
    
    public String getName(String MatNo) {
    	for (int i = 0; i < studentList.size(); i++) {
			if(studentList.get(i).getMatNo().equals(MatNo)) {
				return studentList.get(i).getName();
			}
		}
    	return null;
    }
    
    public void printStudent() {
		System.out.println("\nName | Program |\n");
		for(int i=0;i<studentList.size();i++) {
			System.out.println(studentList.get(i).getName() + "|" + studentList.get(i).getProgram());
		}
		System.out.println();
	}
}