package cz2002assignment;

import java.sql.Time;
import java.util.ArrayList;


public class Course {
	private String code;
	private int noOfIndex;
	private ArrayList<Index> index=new ArrayList<Index>();
	private String school;
	private String programName;
	private String CourseName;
	private int courseYear;
	private String lecLocation;
	private int lecDay;
	private Time lecStartTime;
	private Time lecEndTime;
	private int numAU;
	
	
	public Course(String code, String school, String programName, String CourseName, int CourseYear, String lecLocation, int lecDay, Time lecStartTime, Time lecEndTime, int numAU) {
		this.code = code;
		this.school = school;
		this.programName = programName;
		this.CourseName = CourseName;
		this.courseYear = CourseYear;
		this.lecLocation = lecLocation;
		this.lecDay = lecDay;
		this.lecStartTime = lecStartTime;
		this.lecEndTime = lecEndTime;
		this.numAU = numAU;
		this.noOfIndex=0;
	}
	public Course() {}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNoOfIndex() {
		return noOfIndex;
	}
	public void setNoOfIndex(int noOfIndex) {
		this.noOfIndex = noOfIndex;
	}
	public ArrayList<Index> getIndex() {
		return index;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public int getCourseYear() {
		return courseYear;
	}
	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}
	public String getLecLocation() {
		return lecLocation;
	}
	public void setLecLocation(String lecLocation) {
		this.lecLocation = lecLocation;
	}
	public int getLecDay() {
		return lecDay;
	}
	public void setLecDay(int lecDay) {
		this.lecDay = lecDay;
	}
	public Time getLecStartTime() {
		return lecStartTime;
	}
	public void setLecStartTime(Time lecStartTime) {
		this.lecStartTime = lecStartTime;
	}
	public Time getLecEndTime() {
		return lecEndTime;
	}
	public void setLecEndTime(Time lecEndTime) {
		this.lecEndTime = lecEndTime;
	}
	public int getNumAU() {
		return numAU;
	}
	public void setNumAU(int numAU) {
		this.numAU = numAU;
	}
	public void addIntoIndexList(int indexNum, String indexInfo, int numOfSlot, 
			String tutLocation, int tutDay,Time tutStartTime,Time tutEndTime,String labLocation,
			int labDay,Time labStartTime,Time labEndTime,boolean labIsOdd) {
		index.add(new Index());
		index.get(this.noOfIndex).setIndexNum(indexNum);
		index.get(this.noOfIndex).setIndexInfo(indexInfo);
		index.get(this.noOfIndex).setNoOfslot(numOfSlot);
		index.get(this.noOfIndex).setTutLocation(tutLocation);
		index.get(this.noOfIndex).setTutDay(tutDay);
		index.get(this.noOfIndex).setTutStartTime(tutStartTime);
		index.get(this.noOfIndex).setTutEndTime(tutEndTime);
		index.get(this.noOfIndex).setLabLocation(labLocation);
		index.get(this.noOfIndex).setLabDay(labDay);
		index.get(this.noOfIndex).setLabStartTime(labStartTime);
		index.get(this.noOfIndex).setLabEndTime(labEndTime);
		index.get(this.noOfIndex).setLabisOdd(labIsOdd);
		this.noOfIndex++;
	}
	public void deleteIndex(int indexNum) {
		for (int i = 0; i < noOfIndex; i++) {
			if (index.get(i).getIndexNum()==indexNum) {
				index.remove(i);
				break;
			}
		}
	}
	public int editIndexNum(int originalIndexNum, int newIndexNum) {
			
			for(int i=0; i<index.size();i++) {
				if(index.get(i).getIndexNum()==originalIndexNum) {
					index.get(i).setIndexNum(newIndexNum);
					return 0;
				}
			}
			return -1;
	}
	public boolean checkIndexTaken(int indexNum) {
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i).getIndexNum()==indexNum) {
				return true;
			}
		}
		return false;
		
	}
	public int reduceIndexBy1(int originalIndexNum, boolean reduce) {
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i).getIndexNum()==originalIndexNum) {
				if(reduce) {
					index.get(i).setNoOfEmptySlot(index.get(i).getNoOfEmptySlot()-1);
					return index.get(i).getNoOfEmptySlot()-1;
				}
				else {
					index.get(i).setNoOfEmptySlot(index.get(i).getNoOfEmptySlot()+1);
					return index.get(i).getNoOfEmptySlot()+1;
				}
			}
			
		}
		return -1;//cant find
	}
	public int editNumSlots(int originalIndexNum,int NumSlots) {
		
		for(int i=0; i<index.size();i++) {
			if(index.get(i).getIndexNum()==originalIndexNum) {
				index.get(i).setNoOfslot(NumSlots);
				return 0;
			}
		}
		return -1;
	
}
	public int checkVacancyForIndex(int indexNum) {

		for(int i=0; i<index.size();i++) {
			if(index.get(i).getIndexNum()==indexNum) {
				return index.get(i).getNoOfEmptySlot();
				
			}
		
		}
		return -1;
	}
	
	public int checkSlotsForIndex(int indexNum) {

		for(int i=0; i<index.size();i++) {
			if(index.get(i).getIndexNum()==indexNum) {
				return index.get(i).getNoOfslot();
				
			}
		
		}
		return -1;
	}
	
	
	public int checkNoOfStudInIndex(int indexNum) {

		for(int i=0; i<index.size();i++) {
			if(index.get(i).getIndexNum()==indexNum) {
				return (index.get(i).getNoOfslot()-index.get(i).getNoOfEmptySlot());
			}
		
		}
		return -1;
	}
	public String getCourseCodeByIndex(int indexNo) {
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i).getIndexNum()==indexNo) {
				return getCode();
				
			}
			
		}
		return "Not found";
	}
	public int getAUByIndex(int indexNo) {
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i).getIndexNum()==indexNo) {
				return getNumAU();
				
			}
		}
		return -1;
	}
	
	public ArrayList<ArrayList<String>> getAllTime(int indexNum){
		ArrayList<ArrayList<String>> allTimeSlot= new ArrayList<ArrayList<String>>();
		allTimeSlot.add(new ArrayList<String>());
		
		allTimeSlot.get(0).add(Integer.toString(getLecDay()));
		allTimeSlot.get(0).add(getLecStartTime().toString());
		allTimeSlot.get(0).add(getLecEndTime().toString());
		allTimeSlot.get(0).add(Boolean.toString(false));
		allTimeSlot.get(0).add(Boolean.toString(false));
		
		for(int j=0; j<index.size();j++) {
			if(index.get(j).getIndexNum()==indexNum) {
				allTimeSlot.add(new ArrayList<String>());
				allTimeSlot.add(new ArrayList<String>());
				
				allTimeSlot.get(1).add(Integer.toString(index.get(j).getTutDay()));
				allTimeSlot.get(1).add(index.get(j).getTutStartTime().toString());
				allTimeSlot.get(1).add(index.get(j).getTutEndTime().toString());
				allTimeSlot.get(1).add(Boolean.toString(false));
				allTimeSlot.get(1).add(Boolean.toString(false));
				allTimeSlot.get(2).add(Integer.toString(index.get(j).getLabDay()));
				allTimeSlot.get(2).add(index.get(j).getLabStartTime().toString());
				allTimeSlot.get(2).add(index.get(j).getLabEndTime().toString());
				allTimeSlot.get(2).add(Boolean.toString(true));
				allTimeSlot.get(2).add(Boolean.toString(index.get(j).isLabisOdd()));
					}
				}
			
		
		if(allTimeSlot.size()==3) {
			return allTimeSlot;
		}
		return null;
	}
	
	public ArrayList<String> getInfoFromIndex(int indexNum){
		for (int i = 0; i < index.size(); i++) {
			if(index.get(i).getIndexNum()==indexNum){
				ArrayList<String> indexinfo = new ArrayList<String>();
				indexinfo.add(getCode());
				indexinfo.add(Integer.toString(index.get(i).getIndexNum()));
				indexinfo.add(index.get(i).getIndexInfo());
				indexinfo.add(Integer.toString(index.get(i).getNoOfslot()));
				indexinfo.add(index.get(i).getTutLocation());
				indexinfo.add(Integer.toString(index.get(i).getTutDay()));
				indexinfo.add(index.get(i).getTutStartTime().toString());
				indexinfo.add(index.get(i).getTutEndTime().toString());
				indexinfo.add(index.get(i).getLabLocation());
				indexinfo.add(Integer.toString(index.get(i).getLabDay()));
				indexinfo.add(index.get(i).getLabStartTime().toString());
				indexinfo.add(index.get(i).getLabEndTime().toString());
				indexinfo.add(Boolean.toString(index.get(i).isLabisOdd()));
				return indexinfo;
			}
		}
		return null;
	}
	
	public ArrayList<ArrayList<String>> getCourseInfosByIndex(int indexNo) {//("ClassType|Group|Day|Time|Venue|Remarks");
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for(int j=0;j<index.size();j++) {
			if(index.get(j).getIndexNum()==indexNo) {
				list.add(new ArrayList<String>());
				list.add(new ArrayList<String>());
				list.add(new ArrayList<String>());
				list.get(0).add("Lec");
				list.get(0).add("NA");
				list.get(0).add(Integer.toString(getLecDay()));
				list.get(0).add(getLecStartTime().toString() + " - " + getLecEndTime().toString());
				list.get(0).add(getLecLocation());
				list.get(1).add("Tut");
				list.get(1).add(index.get(j).getIndexInfo());
				list.get(1).add(Integer.toString(index.get(j).getTutDay()));
				list.get(1).add(index.get(j).getTutStartTime().toString() + "-" + index.get(j).getTutEndTime().toString());
				list.get(1).add(index.get(j).getTutLocation());
				list.get(2).add("Lab");
				list.get(2).add(index.get(j).getIndexInfo());
				list.get(2).add(Integer.toString(index.get(j).getLabDay()));
				list.get(2).add(index.get(j).getLabStartTime().toString() + "-" + index.get(j).getLabEndTime().toString());
				list.get(2).add(index.get(j).getLabLocation());
				return list;
				}
			}
		return list;
	}
	
	public void setEmptySlots(int indexNo,int setNo) {
		for(int i=0; i<index.size();i++) {
			if(index.get(i).getIndexNum()==indexNo) {
				index.get(i).setNoOfEmptySlot(setNo);
				return;
			}
		
		}
	}
}
