package cz2002assignment;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StuCouMgr {
	ArrayList<Student_Course> stuCourseList=new ArrayList<Student_Course>();
	private Date accessPeriodStartDate;
	private Date accessPeriodEndDate;
	private Time accessPeriodStartTime;
	private Time accessPeriodEndTime;
	
	public ArrayList<String> registerCourseS(String studName, String matNo, String courseCode, int indexNum, int AU) { 		//Check time overlap
		if(!isRegisteredS(matNo,courseCode)) {
			stuCourseList.add(new Student_Course(studName, matNo, courseCode, indexNum, AU));
		}
		String[] info ={studName, matNo, courseCode, String.valueOf(indexNum), String.valueOf(AU)};
		ArrayList<String> newStudCourse = new ArrayList<String>();
		for (int i = 0; i <5; i++) {
			newStudCourse.add(info[i]);
		}
		return newStudCourse;
	}
	
	public ArrayList<String> registerCourseSWaitlist(String studName, String matNo, String courseCode, int indexNum, LocalDateTime qDatetime, int AU) {
		if(!isRegisteredS(matNo,courseCode)) {
			stuCourseList.add(new Student_Course(studName, matNo, courseCode, indexNum, true, qDatetime, AU));
		}
		String[] info ={studName, matNo, courseCode, String.valueOf(indexNum), String.valueOf(AU),Boolean.toString(true) , qDatetime.toString()};
		ArrayList<String> newWaitList = new ArrayList<String>();
		for (int i = 0; i <7; i++) {
			newWaitList.add(info[i]);
		}
		return newWaitList;
	}
	
	public ArrayList<String> dropCourseS(String matNo, String courseCode) {  							//index add slots
		for(int i=0;i<stuCourseList.size();i++) {
			if(stuCourseList.get(i).getMatNo().equals(matNo)) {
				if(stuCourseList.get(i).getCourseCode().equals(courseCode)) {
					ArrayList<String> out = new ArrayList<String>();
					out.add(stuCourseList.get(i).getStudentName());
					out.add(stuCourseList.get(i).getMatNo());
					out.add(stuCourseList.get(i).getCourseCode());
					out.add(Integer.toString(stuCourseList.get(i).getIndexNum()));
					out.add(Integer.toString(stuCourseList.get(i).getAU()));
					stuCourseList.remove(i);
					return out;
				}
			}
		}
		System.out.println("You did not register for " +courseCode + "!");
		return null;
	}
	
	public boolean isRegisteredS(String matNo, String courseCode) {
		for(int i=0;i<stuCourseList.size();i++) {
			if(matNo.equals(stuCourseList.get(i).getMatNo())&&courseCode.equals(stuCourseList.get(i).getCourseCode())){
				return true;
			}
		}
		return false;
	}
	public boolean isRegisteredIndex(String matNo, int indexNum) {
		for(int i=0;i<stuCourseList.size();i++) {
			if(matNo.equals(stuCourseList.get(i).getMatNo())&&indexNum==stuCourseList.get(i).getIndexNum()){
				return true;
			}
		}
		return false;
	}
	
	public void showRegCourseS(String matNo) {									//Print name at UIApp
		String status;
		int totalAU=0;
		System.out.println("Course" + "|" + "AU" + "|" + "Index Number" + "|" + "Status");
		for(int i=0;i<stuCourseList.size();i++) {
			Student_Course stco = stuCourseList.get(i);
			if(matNo.equals(stco.getMatNo())){
				if(stco.getIsWait()) {
					status = "WAITLIST";
				}else {
					status = "REGISTERED";
					totalAU+=stco.getAU();
				}
				System.out.println(stco.getCourseCode() + "|" + stco.getAU() + "|" +stco.getIndexNum() + "|" + status);
			}
		}
		System.out.println("Total AU REGISTERED "+totalAU+"\n");
	}
	
	public void swapS(String matNum1, String matNum2, String courseCode, int index1, int index2) { //Assume that checked password for matNum2
		int stud1=0;
		int stud2=0;
		for(int i=0;i<stuCourseList.size();i++) {
			if(matNum1.equals(stuCourseList.get(i).getMatNo())) {
				if(index1==stuCourseList.get(i).getIndexNum()) {
					if(courseCode.equals(stuCourseList.get(i).getCourseCode())) {
						stud1=i;
					}
				}
			}
			if(matNum2.equals(stuCourseList.get(i).getMatNo())) {
				if(index2==stuCourseList.get(i).getIndexNum()) {
					if(courseCode.equals(stuCourseList.get(i).getCourseCode())) {
						stud2=i;
					}
				}
			}
		}
		if(stud1==0 || stud2==0) {
			System.out.println("Error! reenter values ");
		}else {
			stuCourseList.get(stud1).setIndexNum(index2);
			stuCourseList.get(stud2).setIndexNum(index1);
		}
	}
	
	public void changeIndexS(String matNo,int curIndex, int newIndex) {			//Check if there is emptyslot
		for(int i=0;i<stuCourseList.size();i++) {								//If no slot, ask in UI if want to add to wait list instead
			if(matNo.equals(stuCourseList.get(i).getMatNo())) {					//then call the matter if yes^
				if(curIndex==stuCourseList.get(i).getIndexNum()) {
					stuCourseList.get(i).setIndexNum(newIndex);
				}
			}
		}
	}
	
	public ArrayList<String> findStudentByIndex(int indexNum) { 
		ArrayList<String> listofStud = new ArrayList<String>();
		for(int i=0;i<stuCourseList.size();i++) {
			if(stuCourseList.get(i).getIndexNum()==indexNum) {
				if(!stuCourseList.get(i).getIsWait()) {
					listofStud.add(stuCourseList.get(i).getMatNo());
				}
			}
		}
		return listofStud;
	}
	
	public ArrayList<String> find1stInWaitListByIndex(int indexNum) { 
		
		ArrayList<ArrayList<String>> listofStudQ = new ArrayList<ArrayList<String>>();
		ArrayList<String> aRecord = new ArrayList<String>();
		for(int i=0;i<stuCourseList.size();i++) {
			
			if(stuCourseList.get(i).getIndexNum()==indexNum) {
				if(stuCourseList.get(i).getIsWait()) {
					listofStudQ.add(new ArrayList<String>());
					listofStudQ.get(listofStudQ.size()-1).add(stuCourseList.get(i).getMatNo());
					listofStudQ.get(listofStudQ.size()-1).add(stuCourseList.get(i).getQdateTime().toString());
				}
			}
		}
		//compare and get earliest		
		if (listofStudQ.size()==0) {
			
			return null;
		}
		else {
			LocalDateTime minDateTime = LocalDateTime.parse(listofStudQ.get(0).get(1));
			String minMatNo=listofStudQ.get(0).get(0);
			for (int i = 1; i < listofStudQ.size(); i++) {
				if(LocalDateTime.parse(listofStudQ.get(i).get(1)).isBefore(minDateTime)) {
					minDateTime = LocalDateTime.parse(listofStudQ.get(i).get(1));
					minMatNo = listofStudQ.get(i).get(0);
				}
			}
			
			Student_Course stucourse =findSingleRecord(minMatNo, indexNum);
			
			String[] info ={stucourse.getStudentName(), minMatNo, stucourse.getCourseCode(), String.valueOf(indexNum), String.valueOf(stucourse.getAU())};
			for (int i = 0; i < info.length; i++) {
				aRecord.add(info[i]);
			}
			stucourse.setIsWait(false);
			stucourse.setQdateTime(null);		
			return aRecord;
		}
	}
	public Student_Course findSingleRecord(String matno,int index) {
		for (int i = 0; i < stuCourseList.size(); i++) {
			if(stuCourseList.get(i).getMatNo().equals(matno) && stuCourseList.get(i).getIndexNum()==(index)) {
				return  stuCourseList.get(i);
			}
		}
		return null;
		
	}
	public ArrayList<String> findStudentByCourse(String coursecode) { 
		ArrayList<String> listofStud = new ArrayList<String>();
		for(int i=0;i<stuCourseList.size();i++) {
			if(stuCourseList.get(i).getCourseCode().equals(coursecode)) {
				if(!stuCourseList.get(i).getIsWait()) {
					listofStud.add(stuCourseList.get(i).getMatNo());
				}
			}
		}
		return listofStud;
	}
	
	public ArrayList<ArrayList<String>> findStudentAllCourse(String matNo){
		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
		for(int i=0;i<stuCourseList.size();i++) {
			if(stuCourseList.get(i).getMatNo().equals(matNo)) {
				out.add(new ArrayList<String>());
				out.get(out.size()-1).add(stuCourseList.get(i).getCourseCode());
				out.get(out.size()-1).add(Integer.toString(stuCourseList.get(i).getIndexNum()));
				if(stuCourseList.get(i).getIsWait()) {
					out.get(out.size()-1).add("WAITLIST");
				}
			}
		}
		return out;
	}
	
	public Date getAccessPeriodStartDate() {
		return accessPeriodStartDate;
	}

	public void setAccessPeriodStartDate(Date accessPeriodStartDate) {
		this.accessPeriodStartDate = accessPeriodStartDate;
	}

	public Date getAccessPeriodEndDate() {
		return accessPeriodEndDate;
	}

	public void setAccessPeriodEndDate(Date accessPeriodEndDate) {
		this.accessPeriodEndDate = accessPeriodEndDate;
	}

	public Time getAccessPeriodStartTime() {
		return accessPeriodStartTime;
	}

	public void setAccessPeriodStartTime(Time accessPeriodStartTime) {
		this.accessPeriodStartTime = accessPeriodStartTime;
	}

	public Time getAccessPeriodEndTime() {
		return accessPeriodEndTime;
	}

	public void setAccessPeriodEndTime(Time accessPeriodEndTime) {
		this.accessPeriodEndTime = accessPeriodEndTime;
	}
	
	public int getStudentAU(String matNo) {
		int total=0;
		for(int i=0;i<stuCourseList.size();i++) {
			if(stuCourseList.get(i).getMatNo().equals(matNo)) {
				total=total+stuCourseList.get(i).getAU();
			}
		}
		return total; 
	}
}
