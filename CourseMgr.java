package cz2002assignment;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

public class CourseMgr {
	
	private ArrayList<Course> course=new ArrayList<Course>();
	
	public ArrayList<String> addCourse(String code,String school, String programName, 
			String CourseName, int CourseYear, String lecLocation, int lecDay, 
			Time lecStartTime, Time lecEndTime, int numAU) {
		Course adding = new Course(code, school, programName, CourseName, CourseYear, 
				lecLocation, lecDay, lecStartTime, lecEndTime, numAU);
		course.add(adding);		
		String[] info ={code,school,programName, CourseName, Integer.toString(CourseYear), 
				lecLocation, Integer.toString(lecDay), lecStartTime.toString(), lecEndTime.toString(), Integer.toString(numAU)};
		
		ArrayList<String> newCourse = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			newCourse.add(info[i]);
		}
		return newCourse;
		

	}
	public void deleteCourse(String courseCode) {
		int i;
		for (i = 0; i < course.size(); i++) {
			if(course.get(i).getCode()==courseCode) {
				course.remove(i);
				System.out.println(courseCode+" Course deleteed!");
				break;
			}
		}
		if (i==course.size()) {
			System.out.println(courseCode+" cannot be found!");
		}
		
	}
	public ArrayList<Course> getCourse() {
		return course;
	}

	public ArrayList<String> editCourseCode(String originalCode, String newCode) {
		
		int i =0;
		for (i=0; i < course.size(); i++) {
			if (newCode.equals(course.get(i).getCode())) {
				System.out.println(newCode+" already taken!\n");
				return null;
			}
		}											//CZ2001|SCSE|CSC|Algorithms|2|LT10|5|08:30:00|09:30:00|3|
		for (i=0; i < course.size(); i++) {
			if (originalCode.equals(course.get(i).getCode())) {
				course.get(i).setCode(newCode);
				System.out.println(originalCode+" changed to "+newCode);
				ArrayList<String> courseInfo = new ArrayList<String>();
				courseInfo.add(course.get(i).getCode());
				courseInfo.add(course.get(i).getSchool());
				courseInfo.add(course.get(i).getProgramName());
				courseInfo.add(course.get(i).getCourseName());
				courseInfo.add(Integer.toString(course.get(i).getCourseYear()));
				courseInfo.add(course.get(i).getLecLocation());
				courseInfo.add(Integer.toString(course.get(i).getLecDay()));
				courseInfo.add(course.get(i).getLecStartTime().toString());
				courseInfo.add(course.get(i).getLecEndTime().toString());
				courseInfo.add(Integer.toString(course.get(i).getNumAU()));
				return courseInfo;
			}
		}
		if (i==course.size()) {
			System.out.println(originalCode+" cannot be found!");
		}
		return null;
	}
	public ArrayList<String> editSchool(String originalCode, String school) {
		
		int i =0;
		for (i=0; i < course.size(); i++) {
			if (originalCode.equals(course.get(i).getCode())) {
				System.out.println(originalCode+"'s school changed to "+school);
				ArrayList<String> courseInfo = new ArrayList<String>();
				courseInfo.add(course.get(i).getCode());
				courseInfo.add(course.get(i).getSchool());
				courseInfo.add(course.get(i).getProgramName());
				courseInfo.add(course.get(i).getCourseName());
				courseInfo.add(Integer.toString(course.get(i).getCourseYear()));
				courseInfo.add(course.get(i).getLecLocation());
				courseInfo.add(Integer.toString(course.get(i).getLecDay()));
				courseInfo.add(course.get(i).getLecStartTime().toString());
				courseInfo.add(course.get(i).getLecEndTime().toString());
				courseInfo.add(Integer.toString(course.get(i).getNumAU()));
				course.get(i).setSchool(school);
				return courseInfo;
			}
		}
		if (i==course.size()) {
			System.out.println(originalCode+" cannot be found!");
		}
		return null;
	}
	public ArrayList<ArrayList<String>> editIndexNum(String originalCode, int originalIndexNum, int newIndexNum) {
		
		if(checkIndexTakenInCourses(newIndexNum)) {
			System.out.println(newIndexNum+" already taken!\n");
			return null;
		}
		for(int i=0; i<course.size();i++) {
			if(originalCode.equals(course.get(i).getCode())) {
				if(!course.get(i).checkIndexTaken(originalIndexNum)) {
					System.out.println(originalIndexNum+" cannot be found");
					return null;
				}
				else{
					System.out.println(originalCode + " "+originalIndexNum+" has been changed to "+newIndexNum+"\n");
					ArrayList<ArrayList<String>> infos= new ArrayList<ArrayList<String>>();
					ArrayList<String> subinfo = new ArrayList<String>();
					subinfo = course.get(i).getInfoFromIndex(originalIndexNum);
					subinfo.set(1, Integer.toString(newIndexNum));
					infos.add(course.get(i).getInfoFromIndex(originalIndexNum));
					infos.add(subinfo);
					course.get(i).editIndexNum(originalIndexNum, newIndexNum);
					return infos;
				}
			}	
			
		}
		System.out.println(originalCode+" cannot be found!");
		return null;
		
}
	private boolean checkIndexTakenInCourses(int indexNum) {
		for (int i = 0; i < course.size(); i++) {
			if (course.get(i).checkIndexTaken(indexNum)) {
				return true;
			}
		}
		return false;
	}
	public void reduceIndexBy1(String originalCode, int originalIndexNum, boolean reduce) {

			int i,newSlot;
			
			for(i=0; i<course.size();i++) {
				if(originalCode.equals(course.get(i).getCode())) {
					newSlot=course.get(i).reduceIndexBy1(originalIndexNum, reduce);
					System.out.println(originalCode+"'s number of slots in  " +originalIndexNum + " updated to "+newSlot);
					break;
				}
			}
			if(i==course.size()){
				System.out.println(originalCode+" cannot be found!??");
				return;
			}
	}
	public void reduceIndexBy1withoutdisplay(String originalCode, int originalIndexNum, boolean reduce) {
		int i;
		
		for(i=0; i<course.size();i++) {
			if(originalCode.equals(course.get(i).getCode())) {
				course.get(i).reduceIndexBy1(originalIndexNum, reduce);
				//System.out.println(originalCode+"'s number of slots in  " +originalIndexNum + " updated to "+newSlot);
				break;
			}
		}
		if(i==course.size()){
			//System.out.println(originalCode+" cannot be found!??");
			return;
		}
	
}
	public ArrayList<ArrayList<String>> editNumSlots(String originalCode, int originalIndexNum,int NumSlots) {
		int i,noOfStud,before;
		//boolean found = false;
		for (i=0; i < course.size(); i++) {
			if (course.get(i).getCode().equals(originalCode)) {
				noOfStud =course.get(i).checkNoOfStudInIndex(originalIndexNum);
				before = course.get(i).checkVacancyForIndex(originalIndexNum)+noOfStud;
				if(noOfStud>NumSlots) {
					System.out.println("Current no of student in " +originalIndexNum+" is larger than input(current: " +course.get(i).checkNoOfStudInIndex(originalIndexNum)+" students)");
					return null;
				}
				if(course.get(i).editNumSlots(originalIndexNum, NumSlots)==0){
					System.out.println(originalCode+"'s index number "+originalIndexNum+ " slots changed to " +NumSlots);
					course.get(i).setEmptySlots(originalIndexNum, NumSlots-noOfStud);
					ArrayList<ArrayList<String>> outinfos = new ArrayList<ArrayList<String>>();
					outinfos.add(course.get(i).getInfoFromIndex(originalIndexNum));
					outinfos.add(course.get(i).getInfoFromIndex(originalIndexNum));
					outinfos.get(0).set(3, Integer.toString(before));
					return outinfos;
					
				}
				else {
					System.out.println(originalIndexNum+" cannot be found!");
					return null;
				}
			}
			
		}
		if (i==course.size()) {
			System.out.println(originalCode+" cannot be found!");
		}
		return null;
}
	
	public int checkVacancyForIndex(int indexNum) {
		int i;
		for (i = 0; i < course.size(); i++) {
				
			int result = course.get(i).checkVacancyForIndex(indexNum);
			if (result!=-1) {

				System.out.println(indexNum+" has " + result +"/" + course.get(i).checkSlotsForIndex(indexNum)+" slots");
				return result;
				 //can't find
			}		
			
		}
		if (i ==course.size()) {
			System.out.println(indexNum+" cannot be found!");
			return -1;
		}
		return -1;
		
	}
	public int checkVacancyForIndexWithoutDisplay(int indexNum) {
		
		int i;
		for (i = 0; i < course.size(); i++) {
				
			int result = course.get(i).checkVacancyForIndex(indexNum);
			if (result!=-1) {

				//System.out.println(indexNum+" has " + result +" slots");
				return result;
				 //can't find
			}		
			
		}
		if (i ==course.size()) {
			//System.out.println(indexNum+" cannot be found!");
			return -1;
		}
		return -1;
		
	}
	public void printCourse() {
		System.out.println("\nCourse Code|Name|Program|AU\n");
		for(int i=0;i<course.size();i++) {
			System.out.println(course.get(i).getCode() + "|" + course.get(i).getCourseName() + "|" + course.get(i).getSchool() + "|" + course.get(i).getNumAU());
		}
		System.out.println();
	}	
	public void addIndex(String courseCode, int indexNum, String indexInfo, int numOfSlot, 
			String tutLocation, int tutDay,Time tutStartTime,Time tutEndTime,String labLocation,
			int labDay,Time labStartTime,Time labEndTime,boolean labIsOdd) {
		for(int i=0;i<course.size();i++) {
			if(course.get(i).getCode().equals(courseCode)) {
				course.get(i).addIntoIndexList(indexNum, indexInfo, numOfSlot, tutLocation, tutDay, tutStartTime, tutEndTime, labLocation,
						labDay, labStartTime, labEndTime, labIsOdd);
				return;
			}
		}
	}
	public ArrayList<ArrayList<String>> getAllTime(String courseCode, int indexNum){
		ArrayList<ArrayList<String>> allTimeSlot= new ArrayList<ArrayList<String>>();
		for (int i = 0; i < course.size(); i++) {
			if (courseCode.equals(course.get(i).getCode())) {
				allTimeSlot = course.get(i).getAllTime(indexNum);
				if(allTimeSlot.size()==3) {
					return allTimeSlot;
				}
			}
		}
		return null;
	}
	public String getCourseCodeByIndex(int indexNo) {
		for(int i=0;i<course.size();i++) {
			if(!(course.get(i).getCourseCodeByIndex(indexNo).equals("Not found"))) {
				return course.get(i).getCourseCodeByIndex(indexNo);
			}
			
		}
		return "Not found";
	}
	public ArrayList<ArrayList<String>> getCourseInfosByIndex(int indexNo) {
		String courseCode ="";
		for (int i = 0; i < course.size(); i++) {
			courseCode = course.get(i).getCourseCodeByIndex(indexNo);
			if (!(courseCode.equals("Not found"))) {
				break;
			}
		}
		for (int i = 0; i < course.size(); i++) {
			if (course.get(i).getCode().equals(courseCode)) {
				return course.get(i).getCourseInfosByIndex(indexNo);
			}
		}
		return null;
		
	}
	public int getAUByIndex(int indexNo) {
		for(int i=0;i<course.size();i++) {
			if(course.get(i).getAUByIndex(indexNo)!=-1) {
				return course.get(i).getAUByIndex(indexNo);
			}
		}
		return -1;
	}
	public boolean isCourseTaken(String courseCode) {
		for(int i=0;i<course.size();i++) {
			if(course.get(i).getCode().equals(courseCode)) {
				return true;
			}
		}
		return false;
	}
}
