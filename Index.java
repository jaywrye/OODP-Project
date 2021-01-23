package cz2002assignment;

import java.sql.Time;


public class Index {
	private int indexNum;
	private String indexInfo;
	private int noOfslot;
	private String tutLocation;
	private int tutDay;
	private Time tutStartTime;
	private Time tutEndTime;
	private String labLocation;
	private int labDay;
	private Time labStartTime;
	private Time labEndTime;
	private boolean labisOdd;
	private int noOfEmptySlot;
	public int getIndexNum() {
		return indexNum;
	}
	public Index() {
		this.noOfEmptySlot=10;
	}
	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}
	public String getIndexInfo() {
		return indexInfo;
	}
	public void setIndexInfo(String indexInfo) {
		this.indexInfo = indexInfo;
	}
	public int getNoOfslot() {
		return noOfslot;
	}
	public void setNoOfslot(int noOfslot) {
		this.noOfslot = noOfslot;
	}
	public String getTutLocation() {
		return tutLocation;
	}
	public void setTutLocation(String tutLocation) {
		this.tutLocation = tutLocation;
	}
	public int getTutDay() {
		return tutDay;
	}
	public void setTutDay(int tutDay) {
		this.tutDay = tutDay;
	}
	public Time getTutStartTime() {
		return tutStartTime;
	}
	public void setTutStartTime(Time tutStartTime) {
		this.tutStartTime = tutStartTime;
	}
	public Time getTutEndTime() {
		return tutEndTime;
	}
	public void setTutEndTime(Time tutEndTime) {
		this.tutEndTime = tutEndTime;
	}
	public String getLabLocation() {
		return labLocation;
	}
	public void setLabLocation(String labLocation) {
		this.labLocation = labLocation;
	}
	public int getLabDay() {
		return labDay;
	}
	public void setLabDay(int labDay) {
		this.labDay = labDay;
	}
	public Time getLabStartTime() {
		return labStartTime;
	}
	public void setLabStartTime(Time labStartTime) {
		this.labStartTime = labStartTime;
	}
	public Time getLabEndTime() {
		return labEndTime;
	}
	public void setLabEndTime(Time labEndTime) {
		this.labEndTime = labEndTime;
	}
	public boolean isLabisOdd() {
		return labisOdd;
	}
	public void setLabisOdd(boolean labisOdd) {
		this.labisOdd = labisOdd;
	}
	public int getNoOfEmptySlot() {
		return noOfEmptySlot;
	}
	public void setNoOfEmptySlot(int noOfEmptySlot) {
		this.noOfEmptySlot = noOfEmptySlot;
	}
	public void calNoOfEmptySlot(int val) {
		noOfEmptySlot += val;
	}
	public boolean isAvailable(){
		if (this.noOfEmptySlot>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
}
