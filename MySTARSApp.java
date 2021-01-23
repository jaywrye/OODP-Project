package cz2002assignment;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.Console;

public class MySTARSApp {//("C:\\Users\\Jason Lim\\OneDrive\\Desktop\\course.txt");("C:\\Users\\Jason Lim\\OneDrive\\Desktop\\student.txt");

	
	public static void main(String[] args) {
		context contxt = new context(new SendEmail());
		String algorithm = "SHA3-256";
		CourseMgr courseMgr = new CourseMgr();
		AdminMgr adminMgr= new AdminMgr();
		StudentMgr studentMgr = new StudentMgr();
		StuCouMgr studentCourseMgr = new StuCouMgr();
		UserLoginMgr userloginmgr = new UserLoginMgr();
		String readStudentFile = "/Users/nikomii/Desktop/data/student.txt";
		String readCourseFile  = "/Users/nikomii/Desktop/data/course.txt";
		String readIndexFile  = "/Users/nikomii/Desktop/data/index.txt";
		String readUserLoginFile  = "/Users/nikomii/Desktop/data/userlogin.txt";
		String readStudCourseFile  = "/Users/nikomii/Desktop/data/studentCourse.txt";
		String readAccessPeriod  = "/Users/nikomii/Desktop/data/accessperiod.txt";
//		String readStudentFile = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\student.txt";
//		String readCourseFile  = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\course.txt";
//		String readIndexFile  = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\index.txt";
//		String readUserLoginFile  = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\userlogin.txt";
//		String readStudCourseFile  = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\studentCourse.txt";
//		String readAccessPeriod  = "C:\\Users\\Jason Lim\\OneDrive\\Desktop\\accessperiod.txt";
		ArrayList<ArrayList<String> > courseRec = new ArrayList<ArrayList<String> >(); 
		ArrayList<ArrayList<String> > studentRec = new ArrayList<ArrayList<String> >(); 
		ArrayList<ArrayList<String> > indexRec = new ArrayList<ArrayList<String> >(); 
		ArrayList<ArrayList<String> > loginRec = new ArrayList<ArrayList<String> >();
		ArrayList<ArrayList<String> > courseStudRec = new ArrayList<ArrayList<String> >(); 
		ArrayList<ArrayList<String> > readAccessRec = new ArrayList<ArrayList<String> >(); 

        courseRec = FileHandler.readFileByLines(readCourseFile);
        //load course record
        for (int i = 0; i < courseRec.size(); i++) {
            
            courseMgr.addCourse(courseRec.get(i).get(0), 
            		courseRec.get(i).get(1), 
            		courseRec.get(i).get(2), 
            		courseRec.get(i).get(3),
            		Integer.parseInt(courseRec.get(i).get(4)), 
            		courseRec.get(i).get(5), 
            		Integer.parseInt(courseRec.get(i).get(6)), //int
            		Time.valueOf(courseRec.get(i).get(7)), //time
            		Time.valueOf(courseRec.get(i).get(8)), //time
            		Integer.parseInt(courseRec.get(i).get(9)));//int
                
        }    
        
        studentRec = FileHandler.readFileByLines(readStudentFile);
        //load student record
		for (int i = 0; i < studentRec.size(); i++) {
		            
		            studentMgr.addStudent(studentRec.get(i).get(0), 
		            		studentRec.get(i).get(1), 
		            		studentRec.get(i).get(2), 
		            		studentRec.get(i).get(3).charAt(0),
		            		studentRec.get(i).get(4), 
		            		Integer.parseInt(studentRec.get(i).get(5)),
		            		studentRec.get(i).get(6), 
		            		studentRec.get(i).get(7));
		        }  

		 indexRec = FileHandler.readFileByLines(readIndexFile);
		 //load student record
		 for(int i = 0; i < indexRec.size(); i++) {
				courseMgr.addIndex(indexRec.get(i).get(0), 
						Integer.parseInt(indexRec.get(i).get(1)), 
						indexRec.get(i).get(2), 
						Integer.parseInt(indexRec.get(i).get(3)), 
						indexRec.get(i).get(4), 
						Integer.parseInt(indexRec.get(i).get(5)), 
						Time.valueOf(indexRec.get(i).get(6)), 
						Time.valueOf(indexRec.get(i).get(7)), 
						indexRec.get(i).get(8), 
						Integer.parseInt(indexRec.get(i).get(9)), 
						Time.valueOf(indexRec.get(i).get(10)), 
						Time.valueOf(indexRec.get(i).get(11)), 
						Boolean.parseBoolean(indexRec.get(i).get(12)));}
		 
		 
		 loginRec = FileHandler.readFileByLines(readUserLoginFile);
		 //load student record
		 for(int i = 0; i < loginRec.size(); i++) {
			 userloginmgr.addUser(loginRec.get(i).get(0), loginRec.get(i).get(1), loginRec.get(i).get(2).charAt(0));
		 }
		 
		 readAccessRec = FileHandler.readFileByLines(readAccessPeriod);
		 studentCourseMgr.setAccessPeriodStartDate(Date.valueOf(readAccessRec.get(0).get(0)));
			studentCourseMgr.setAccessPeriodEndDate(Date.valueOf(readAccessRec.get(0).get(1)));
			studentCourseMgr.setAccessPeriodStartTime(Time.valueOf(readAccessRec.get(0).get(2)));
			studentCourseMgr.setAccessPeriodEndTime(Time.valueOf(readAccessRec.get(0).get(3)));
		 
		 
		 courseStudRec = FileHandler.readFileByLines(readStudCourseFile);
		 //load student record
		 for(int i = 0; i < courseStudRec.size(); i++) {
			 if(courseStudRec.get(i).size()==5) {
				 studentCourseMgr.registerCourseS(courseStudRec.get(i).get(0), 
						 courseStudRec.get(i).get(1), 
						 courseStudRec.get(i).get(2), 
						 Integer.parseInt(courseStudRec.get(i).get(3)), 
						 Integer.parseInt(courseStudRec.get(i).get(4)));
				 courseMgr.reduceIndexBy1withoutdisplay(courseStudRec.get(i).get(2), Integer.parseInt(courseStudRec.get(i).get(3)), true);
			 }else {
				 studentCourseMgr.registerCourseSWaitlist(courseStudRec.get(i).get(0), 
						 courseStudRec.get(i).get(1), 
						 courseStudRec.get(i).get(2), 
						 Integer.parseInt(courseStudRec.get(i).get(3)),
						 LocalDateTime.parse(courseStudRec.get(i).get(6)), 
						 Integer.parseInt(courseStudRec.get(i).get(4)));
			 
		 }
	}
		
		String loginID="";
		Console console = System.console();
		
		do {
			System.out.print("Enter your LoginID (Student LoginId will be your Mat Number) : ");
			Scanner sc = new Scanner(System.in);
			loginID = sc.next();			
			//System.out.print("Please enter your password: ");
			//String password = sc.next();
			char[] password = console.readPassword("Please enter your password:");
			String psword = new String(password);
			System.out.println();
			char role = userloginmgr.validateLogin(loginID, psword);
			if (Character.compare(role, '?')==0) {
				if(studentMgr.isMatTaken(loginID)) {
					role = 'S';
					System.out.print("This is your first time logging in, Please enter password for future use :");
					String newpassword = sc.next();
					userloginmgr.addUserWithHash(loginID, newpassword, role);
					System.out.println("\nYour user account has been successfully created.\n");
					ArrayList<String> addNewUser = new ArrayList<String>();
					addNewUser.add(loginID);
					addNewUser.add(userloginmgr.getpassword(loginID));
					addNewUser.add(Character.toString(role));
					try {
						FileHandler.writeFile1(readUserLoginFile, addNewUser);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			 
			
			switch(role) {
				case 'A': {
					int choice;
					String input = "";
					do {
						System.out.println("Hello, welcome to MySTARS");
						System.out.println("Perform the following methods:");
						System.out.println("(1) Edit student access period");
						System.out.println("(2) Add a student");
						System.out.println("(3) Add a course/index ");
						System.out.println("(4) Update a course");
						System.out.println("(5) Check available slot for an index number (vacancy in a class)");
						System.out.println("(6) Print student list by index number");
						System.out.println("(7) Print student list by course (all students registered for the selected course)");
						System.out.println("(8) Log Out");
						System.out.print("\n  Enter the number of your choice: ");
						while(!sc.hasNextInt()) {
							System.out.println("  Please enter integer only");
							System.out.print("\n  Enter the number of your choice: ");
							sc.next();
						}
						choice = sc.nextInt();
						
						switch(choice) {
							case 1: {
								ArrayList<String> editfile = new ArrayList<String>();
								ArrayList<String> deletefile = new ArrayList<String>();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
						        sdf.setLenient(false);
								System.out.println("Start Date" +"\t"+ "Start Time" +"\t\t"+ "End Date" +"\t"+ "End Time");
								System.out.println(studentCourseMgr.getAccessPeriodStartDate() +"\t"+  studentCourseMgr.getAccessPeriodStartTime()
												+"\t\t"+ studentCourseMgr.getAccessPeriodEndDate() +"\t"+ studentCourseMgr.getAccessPeriodEndTime());
								deletefile.add(studentCourseMgr.getAccessPeriodStartDate().toString());
								deletefile.add(studentCourseMgr.getAccessPeriodEndDate().toString());
								deletefile.add(studentCourseMgr.getAccessPeriodStartTime().toString());
								deletefile.add(studentCourseMgr.getAccessPeriodEndTime().toString());
								System.out.println("\n  Enter access period Start Date (Example: 2015-03-31)");
								input = sc.next();
								Date accessPeriodStartDate=Date.valueOf("2020-10-30");
								try{
						            sdf.parse(input);
						            accessPeriodStartDate=Date.valueOf(input);
						        }
						        catch(Exception e)
						        {
						            System.out.println(input+" is not a valid Date\n");
						            break;
						        }
								editfile.add(input);
								System.out.println("\n  Enter access period End Date (Example: 2015-03-31)");
								input = sc.next();
								Date accessPeriodEndDate=Date.valueOf("2020-11-30");
								try{
						            sdf.parse(input);
						            accessPeriodEndDate=Date.valueOf(input);
						        }
						        catch(Exception e)
						        {
						            System.out.println(input+" is not a valid Date\n");
						            break;
						        }
								editfile.add(input);
								LocalDate startingdate = accessPeriodStartDate.toLocalDate();
								LocalDate endingdate = accessPeriodEndDate.toLocalDate();
								
								
								if(startingdate.isAfter(endingdate)) {
									System.out.println("Start date must be before End date");
									break;
								}else {
									studentCourseMgr.setAccessPeriodStartDate(accessPeriodStartDate);
									studentCourseMgr.setAccessPeriodEndDate(accessPeriodEndDate);
								}
								//time
								
								
								System.out.println("\n  Enter access period Start Time (Example: 10:34:34)");
								input = sc.next();
								Time accessPeriodStartTime = Time.valueOf("10:00:00");
								try{
						            stf.parse(input);
						            accessPeriodStartTime = Time.valueOf(input);
						        }
						        catch(Exception e)
						        {
						            System.out.println(input+" is not a valid Time\n");
						            break;
						        }
								editfile.add(input);
								studentCourseMgr.setAccessPeriodStartTime(accessPeriodStartTime);
								System.out.println("\n  Enter access period End Time (Example: 10:34:34)");
								input = sc.next();
								Time accessPeriodEndTime = Time.valueOf("17:00:00");
								
								if(startingdate.isEqual(endingdate)) {
									LocalTime startingtime = accessPeriodStartTime.toLocalTime();
									LocalTime endingtime = accessPeriodEndTime.toLocalTime();
									if(startingtime.isAfter(endingtime)) {
										System.out.println("Start Date time must be before End Date time\n");
										break;
									}
								}
								
								try{
						            stf.parse(input);
						            accessPeriodEndTime = Time.valueOf(input);
						        }
						        catch(Exception e)
						        {
						            System.out.println(input+" is not a valid Time\n");
						            break;
						        }
								editfile.add(input);
								studentCourseMgr.setAccessPeriodEndTime(accessPeriodEndTime);
								try {
									FileHandler.writeFileDelerow(readAccessPeriod, deletefile);
									FileHandler.writeFile1(readAccessPeriod, editfile);
									System.out.println("You have successfully changed access period. \nCurrent period:");
									System.out.println("Start Date" +"\t"+ "Start Time" +"\t\t"+ "End Date" +"\t"+ "End Time");
									System.out.println(studentCourseMgr.getAccessPeriodStartDate() +"\t"+  studentCourseMgr.getAccessPeriodStartTime()
													+"\t\t"+ studentCourseMgr.getAccessPeriodEndDate() +"\t"+ studentCourseMgr.getAccessPeriodEndTime()+"\n");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							}
							case 2: {
								String[] list1= {"Matric Number", "Name", "Email-Address", "School","Program","Nationality"};
								
								String[] input1 = new String[list1.length];
								input = sc.nextLine();
								boolean matTaken = false;
								for(int i=0;i<list1.length;i++) {
									System.out.println("Please enter " + list1[i] + " : ");
									input = sc.nextLine();
									input1[i]=input;
									if(i==0) {
										if(studentMgr.isMatTaken(input1[i])) {
											matTaken = true;
											break;
										}
									}
								}
								if(matTaken) {
									System.out.println("Matric number is already in use\n");
									break;
								}
								char gender='s';
								
								System.out.println("Please enter gender (M / F) : ");
								gender = sc.next().charAt(0);
								
								while(!(Character.compare(gender, 'M')==0 || Character.compare(gender, 'F')==0)) {
									System.out.println("  Please enter M or F only");
									System.out.println("Please enter gender (M / F) : ");
									gender = sc.next().charAt(0);
								}
								int studYear=-1;
								do{
									System.out.println("Please enter study year : ");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Please enter study year : ");
										sc.next();
									}
									studYear = sc.nextInt();
									if(studYear<=0 || studYear>4) {
										System.out.print("  Please enter study year between 1-4:");
									}
								}while(studYear<=0||studYear>4);
								
								System.out.println("Student successfully added!");	
								
								ArrayList<String> writeData = new ArrayList<String>();
								writeData = studentMgr.addStudent(input1[0],input1[1],input1[2],gender,input1[3],studYear,input1[4],input1[5]);
								try {
									FileHandler.writeFile1(readStudentFile,writeData);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								studentMgr.printStudent();
								
								break;
							}
							case 3: 
								{
									String[] list1= {"Course Code", "School", "programName", "CourseName",  "lecLocation"};
									
									String[] input1 = new String[list1.length];
									boolean courseTaken=false;
									sc.nextLine();
									for(int i=0;i<list1.length;i++) {
										System.out.println("Please enter " + list1[i] + " : ");
										input = sc.nextLine();
										input1[i]=input;
										if(i==0) {
											if(courseMgr.isCourseTaken(input1[i])) {
												courseTaken = true;
												break;
											}
										}
									}
									
									if(courseTaken) {
										System.out.println(" Course Code is currently in use \n");
										break;
									}
									
									int courseYear=-1;
									do{
										System.out.println("Please enter Course Year : ");
										while(!sc.hasNextInt()) {
											System.out.println("  Please enter integer only");
											System.out.println("Please enter Course Year: ");
											sc.next();
										}
										courseYear = sc.nextInt();
										if(courseYear<=0 || courseYear>4) {
											System.out.print("  Please enter Course Year between 1-4:");
										}
									}while(courseYear<=0||courseYear>4);
									
									int lecDay=-1;
									do{
										System.out.println("Please enter Lecture Day (1)Monday (2)Tuesday (3)Wednesday  (4)Thursday (5)Friday: ");
										while(!sc.hasNextInt()) {
											System.out.println("  Please enter integer only");
											System.out.println("Please enter Lecture Day (1)Monday (2)Tuesday (3)Wednesday  (4)Thursday (5)Friday: ");
											sc.next();
										}
										lecDay = sc.nextInt();
										if(lecDay<=0 || lecDay>5) {
											System.out.println("  Please enter Day between 1-5:");
										}
									}while(lecDay<=0||lecDay>5);
									
									int AU=-1;
									do{
										System.out.println("Please enter AU for the course: ");
										while(!sc.hasNextInt()) {
											System.out.println("  Please enter integer only");
											System.out.println("Please enter AU for the course: ");
											sc.next();
										}
										AU = sc.nextInt();
										if(AU<=0 || AU>10) {
											System.out.println("  Please enter AU between 1-10:");
										}
									}while(AU<=0||AU>10);
								
									SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
									boolean timecorrect=false;
									Time lecStartTime = Time.valueOf("00:00:00");
									Time lecEndTime = Time.valueOf("00:00:00");
									System.out.println("Enter Lecture Start Time (Example: 10:34:34)");
									input = sc.next();
									try{
							            stf.parse(input);
							            lecStartTime = Time.valueOf(input);
							            timecorrect=true;
							        }
							        catch(Exception e)
							        {
							            timecorrect = false;
							        }
									
									if(!timecorrect) {
										System.out.println(input+" is not a valid Time\n");
										break;
									}
									System.out.println("Enter Lecture End Time (Example: 10:34:34)");
									input = sc.next();
									try{
							            stf.parse(input);
							            lecEndTime = Time.valueOf(input);
							            timecorrect=true;
							        }
							        catch(Exception e)
									{
							            timecorrect = false;
							        }
									
									if(!timecorrect) {
										System.out.println(input+" is not a valid Time\n");
										break;
									}
									
									if(lecStartTime.compareTo(lecEndTime)>0) {
										System.out.println("Start time must be before End time\n");
										break;
									}
								
									System.out.println("Course successfully added!");
									ArrayList<String> writeCourseData = new ArrayList<String>();
									writeCourseData = courseMgr.addCourse(input1[0],input1[1],input1[2],input1[3],courseYear,input1[4],lecDay,lecStartTime,lecEndTime,AU);
									try {
										FileHandler.writeFile1(readCourseFile,writeCourseData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									courseMgr.printCourse();
									break;
								}
							case 4:{ //course code, school, its index numbers and vacancy
								int choice4=-1;
								do{
									System.out.println("Which details of course do you want to update?");
									System.out.println("(1) Update Course Code");
									System.out.println("(2) Update School");
									System.out.println("(3) Update Index numbers");
									System.out.println("(4) Vacancy");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Please enter choice: ");
										sc.next();
									}
									choice4 = sc.nextInt();
									if(choice4<=0 || choice4>4) {
										System.out.print("  Please enter between 1-4:");
									}
								}while(choice4<=0||choice4>4);
								
								switch(choice4) {
								case 1:{
									System.out.println("Which course code would you like to change : ");
									String originalCode = sc.next();
									System.out.println("Enter new course code to change into : ");
									String newCode = sc.next();
									ArrayList<String> writeCourseData = new ArrayList<String>();
									writeCourseData = courseMgr.editCourseCode(originalCode, newCode);
									if(writeCourseData==null) {
										break;
									}
									try {
										FileHandler.writeFileDelerow(readCourseFile,writeCourseData);
										FileHandler.writeFile1(readCourseFile,writeCourseData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
									}
								case 2:{
									System.out.println("Which course code would you like to change : ");
									String originalCode = sc.next();
									System.out.println("Enter new school to change into : ");
									String newSchool = sc.next();
									ArrayList<String> writeCourseData = new ArrayList<String>();
									writeCourseData = courseMgr.editSchool(originalCode, newSchool);
									if(writeCourseData==null) {
										System.out.println("No such course code\n");
										break;
									}
									ArrayList<String> previousCourseData =new ArrayList<String>();
									for(int i=0;i<writeCourseData.size();i++) {
										previousCourseData.add(writeCourseData.get(i));
									}
									writeCourseData.set(1, newSchool);
									try {
										FileHandler.writeFileDelerow(readCourseFile,previousCourseData);
										FileHandler.writeFile1(readCourseFile,writeCourseData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
									}
								case 3:{
									System.out.println("Which course would you like to change its index number : ");
									String originalCode = sc.next();
									int originalIndexNum,newIndexNum;
									System.out.println("Which index would you like to change : ");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Which index would you like to change : ");
										sc.next();
									}
									originalIndexNum = sc.nextInt();
									System.out.println("Give a new index number would you like to change to: ");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Give a new index number would you like to change to: ");
										sc.next();
									}
									newIndexNum = sc.nextInt();
									ArrayList<ArrayList<String>> writeDatafile = new ArrayList<ArrayList<String>>();
									writeDatafile=courseMgr.editIndexNum(originalCode, originalIndexNum, newIndexNum);
									if(writeDatafile==null) {
										break;
									}else if(writeDatafile.size()<2) {
										break;
									}
									try {
										FileHandler.writeFileDelerow(readIndexFile,writeDatafile.get(0));
										FileHandler.writeFile1(readIndexFile,writeDatafile.get(1));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
									}
								case 4:{
									System.out.println("Which course code's vacancy would you like to change : ");
									String originalCode = sc.next();
									int newVac,indexChange;
									System.out.println("Which index's vacancy would you like to change : ");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Which index's vacancy would you like to change : ");
										sc.next();
									}
									indexChange = sc.nextInt();
									System.out.println("Enter vacancy to change into : ");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.println("Enter vacancy to change into : ");
										sc.next();
									}
									newVac = sc.nextInt();
									
									
									
									ArrayList<ArrayList<String>> writeDatafile = new ArrayList<ArrayList<String>>();
									writeDatafile=courseMgr.editNumSlots(originalCode,indexChange, newVac);
									if(writeDatafile==null) {
										break;
									}else if(writeDatafile.size()<2) {
										break;
									}
									try {
										FileHandler.writeFileDelerow(readIndexFile,writeDatafile.get(0));
										FileHandler.writeFile1(readIndexFile,writeDatafile.get(1));
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									break;
									}
								}
								break;
							}
							case 5:{
								System.out.println("Enter index for vacancy number : ");
								while(!sc.hasNextInt()) {
									System.out.println("  Please enter integer only");
									System.out.println("Enter index for student list : ");
									sc.next();
								}
								int indexNum = sc.nextInt();
								courseMgr.checkVacancyForIndex(indexNum);
								break;
							}
							case 6:{
								System.out.println("Enter index for student list : ");
								while(!sc.hasNextInt()) {
									System.out.println("  Please enter integer only");
									System.out.println("Enter index for student list : ");
									sc.next();
								}
								int indexNum = sc.nextInt();
								ArrayList <String> listofStud= studentCourseMgr.findStudentByIndex(indexNum);
								if(listofStud.size()==0) {
									System.out.println("There are no such index/no student in "+indexNum+" ! \n");
								}else {
									studentMgr.findStudentwithList(listofStud);
									System.out.println("");
								}
								break;	
								}	
							case 7:{
								System.out.println("Enter course for student list : ");
								String coursecode = sc.next();
								ArrayList <String> listofStud= studentCourseMgr.findStudentByCourse(coursecode);
								if(listofStud.size()==0) {
									System.out.println("There are no such course/no student in "+ coursecode +" ! \n");
								}else {
									studentMgr.findStudentwithList(listofStud);
									System.out.println("");
								}
								
								break;	
								}	
							default:
								break;
						}
						
					}while (choice != 8);
				
						break;}
				case 'S': {
					int choice;
					String input = "";
					Date startdate = studentCourseMgr.getAccessPeriodStartDate();
					Date enddate = studentCourseMgr.getAccessPeriodEndDate();
					Time starttime = studentCourseMgr.getAccessPeriodEndTime();
					Time endtime = studentCourseMgr.getAccessPeriodEndTime();
					LocalDateTime starting = LocalDateTime.of(startdate.toLocalDate(), starttime.toLocalTime());
					LocalDateTime ending = LocalDateTime.of(enddate.toLocalDate(), endtime.toLocalTime());
					LocalDateTime now = LocalDateTime.now();
					boolean withinaccess=false;
					if(starting.isBefore(now)||starting.isEqual(now)) {
						if(ending.isAfter(now)||ending.isEqual(now)) {
							withinaccess=true;
						}
					}
					if(!withinaccess) {
						System.out.println("You are only able to login within the access period\n");
						break;
					}
					
					do {
						System.out.println("Hello, welcome to MySTARS");
						System.out.println("Perform the following methods:");
						System.out.println("(1) Add Course");
						System.out.println("(2) Drop Course");
						System.out.println("(3) Check/Print Courses Registered");
						System.out.println("(4) Check Vacancies Available");
						System.out.println("(5) Change Index Number of Course");
						System.out.println("(6) Swap Index Number with Another Student ");
						System.out.println("(7) Print Time Table");
						System.out.println("(8) Log Out");
						System.out.print("\n  Enter the number of your choice: ");
						while(!sc.hasNextInt()) {
							System.out.println("  Please enter integer only");
							System.out.print("\n  Enter the number of your choice: ");
							sc.next();
						}
						choice = sc.nextInt();
						
						switch(choice) {
						case 1: {								//Case U1900001H add index 10127 (Fail clash) 10126(Pass) 10125(Fail course taken)
							System.out.println("Enter index number");
							while(!sc.hasNextInt()) {
								System.out.println("Enter index number");
								System.out.println("  Please enter integer only");
								System.out.print("\n  Enter index number: ");
								sc.next();
							}
							int indexNo = sc.nextInt();
							String courseCode = courseMgr.getCourseCodeByIndex(indexNo);
							if(studentCourseMgr.isRegisteredS(loginID, courseCode)) {
								System.out.println("You are already registered for this course \n ");
								break;
							}
							if(courseCode.equals("Not found")) {
								System.out.println("No such index \n");
								break;
							}
							
							if(studentCourseMgr.getStudentAU(loginID)+courseMgr.getAUByIndex(indexNo)>=22) {
								System.out.println("The max AU you can take is 21\n");
								break;
							}
							ArrayList<ArrayList<String>> courseInfo = courseMgr.getCourseInfosByIndex(indexNo);
							System.out.println("\nCourseCode : "+courseCode);
							System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
							for(int i=0;i<courseInfo.size();i++) {
								courseInfo.get(i).set(2, DayOfWeek.of(Integer.parseInt(courseInfo.get(i).get(2))).toString());
							}
							for(int i=0;i<courseInfo.size();i++) {
								for(int j=0;j<courseInfo.get(i).size();j++) {
									System.out.print(courseInfo.get(i).get(j)+"|");
								}
								System.out.print("\n");
							}
							System.out.print("\n");
							char confirm1='?';
							do {
								System.out.println("Confirm? Y/N");
								confirm1 = sc.next().charAt(0);
							}while(!(confirm1=='Y'||confirm1=='N'));
							if(Character.compare(confirm1, 'N')==0) {
								System.out.println("Cancelled\n");
								break;
							}
							ArrayList<ArrayList<String>> timeslots= studentCourseMgr.findStudentAllCourse(loginID);
							ArrayList<ArrayList<ArrayList<String>>> alltocheck = new ArrayList<ArrayList<ArrayList<String>>>();
							for(int i=0;i<timeslots.size();i++) {
								alltocheck.add(courseMgr.getAllTime(timeslots.get(i).get(0), Integer.parseInt(timeslots.get(i).get(1))));
							}
							Boolean clash=false;
							for(int i=0;i<alltocheck.size();i++) {
								for(int j=0;j<alltocheck.get(i).size();j++) {
									if(collision(courseCode, indexNo,  Integer.parseInt(alltocheck.get(i).get(j).get(0)), 
											Time.valueOf(alltocheck.get(i).get(j).get(1)), Time.valueOf(alltocheck.get(i).get(j).get(2)), 
											Boolean.parseBoolean(alltocheck.get(i).get(j).get(3)), Boolean.parseBoolean(alltocheck.get(i).get(j).get(4)),courseMgr)) {
										System.out.println("There are clashing time slots \n");
										clash = true;
										break;
									}
								}
							}
							if(clash) {
								break;
							}
							int emptySlots=courseMgr.checkVacancyForIndexWithoutDisplay(indexNo);
							if(emptySlots<0) {
								System.out.println("No such index ");
							}else{
								if(emptySlots==0){
									do {
										System.out.println("There is no vacancy in index "+ indexNo);
										System.out.println("Do you want to add index to wait list Y/N");
										confirm1 = sc.next().charAt(0);
									}while(!(confirm1=='Y'||confirm1=='N'));
									
									if(Character.compare(confirm1, 'N')==0) {
										System.out.println("Cancelled\n");
										break;
									}						
									ArrayList<String> writeCourseData = new ArrayList<String>();
									writeCourseData = studentCourseMgr.registerCourseSWaitlist(studentMgr.findStudentwithMat(loginID), loginID, 
											courseCode, indexNo, LocalDateTime.now(), courseMgr.getAUByIndex(indexNo));
									
									try {
										FileHandler.writeFile1(readStudCourseFile,writeCourseData);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}else {
								courseMgr.reduceIndexBy1withoutdisplay(courseMgr.getCourseCodeByIndex(indexNo), indexNo, true);
								
								ArrayList<String> writeCourseData = new ArrayList<String>();
								writeCourseData = studentCourseMgr.registerCourseS(studentMgr.findStudentwithMat(loginID), loginID, 
										courseCode, indexNo, courseMgr.getAUByIndex(indexNo));
								try {
									FileHandler.writeFile1(readStudCourseFile,writeCourseData);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
							}
							System.out.println("You have successfully added "+courseCode +" with index : "+indexNo+"\n");
						
							break;}
						case 2: {
							ArrayList<ArrayList<String>> listofinfos= studentCourseMgr.findStudentAllCourse(loginID);
							for(int i=0;i<listofinfos.size();i++) {
								System.out.print(+i+1+")|");
								for(int j=0;j<listofinfos.get(i).size();j++) {
									System.out.print(listofinfos.get(i).get(j)+"|");
									}
								System.out.println("");
								}			
								do {
									System.out.println("\nSelect which to drop (Must between 1 to "+listofinfos.size()+")("+(listofinfos.size()+1)+" to skip)");
									while(!sc.hasNextInt()) {
										System.out.println("  Please enter integer only");
										System.out.print("\n  Enter the number of your choice: ");
										sc.next();
									}
									choice = sc.nextInt();
									choice--;
								}while(!(choice<(listofinfos.size()+1)));
								if(choice==(listofinfos.size())) {
									break;
								}
								//System.out.println(listofinfos.get(choice).get(0) is courseCode
								//System.out.println(listofinfos.get(choice).get(1) is indexNum
								
								
								ArrayList<String> writeCourseData = new ArrayList<String>();
								writeCourseData = studentCourseMgr.dropCourseS(loginID, listofinfos.get(choice).get(0));
								
								ArrayList<String> writeEditCourseData = new ArrayList<String>();
								writeEditCourseData = studentCourseMgr.find1stInWaitListByIndex(Integer.parseInt(listofinfos.get(choice).get(1)));
								
								try {
									if (!(writeEditCourseData == null)) {
										FileHandler.writeFileDelerow(readStudCourseFile,writeCourseData);
										FileHandler.writeFileDelerow(readStudCourseFile,writeEditCourseData);
										FileHandler.writeFile1(readStudCourseFile, writeEditCourseData);
										String to = studentMgr.getEmailByMatNo(writeEditCourseData.get(1));
										String subject = "Waitlist notification";
										String body = "Dear " + writeEditCourseData.get(0) + " (MatNo: " + writeEditCourseData.get(1) +")" +
												"\nThe folowing course index has been added into Waitlist.\n" + 
												"Course Code: " + writeEditCourseData.get(2) + 
												"\n Index No: " + writeEditCourseData.get(3) ;
										contxt.executeStrategy(to, subject, body);
									}
									
									else {
										FileHandler.writeFileDelerow(readStudCourseFile,writeCourseData);
										courseMgr.reduceIndexBy1(listofinfos.get(choice).get(0), Integer.parseInt(listofinfos.get(choice).get(1)), false);
									}
									System.out.println("You have successfully drop "+ listofinfos.get(choice).get(0)+" index: "+ listofinfos.get(choice).get(1));
									System.out.println();
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							break;
							}
						case 3: {
							studentCourseMgr.showRegCourseS(loginID);
							break;
							}
						case 4: {
							System.out.println("Enter index for vacancy number : ");
							while(!sc.hasNextInt()) {
								System.out.println("  Please enter integer only");
								System.out.println("Enter index for student list : ");
								sc.next();
							}
							int indexNum = sc.nextInt();
							courseMgr.checkVacancyForIndex(indexNum);
							System.out.println("\n");
							break;
						}
						case 5: {						
							//1. check new index vacancy
							//if got enough slot:
								//add new index record into student_course
								//delete old index from student_course
								//old index slot +1
								//new index slot -1
							//else not enough slot:
								//output no slot for the new index, cannot change
							System.out.println("Enter current index number that you would like to change");
							while(!sc.hasNextInt()) {
								System.out.println("Enter current index number that you would like to change");
								System.out.println("  Please enter integer only");
								sc.next();
							}
							
							int curindexNo = sc.nextInt();
							if ((courseMgr.checkVacancyForIndexWithoutDisplay(curindexNo)<0)) {
								System.out.println("Invalid index number");
								break;
							}
							else if(!(studentCourseMgr.isRegisteredIndex(loginID, curindexNo))) {
								System.out.println("You are not registered for this index");
								break;
							}
							
							System.out.println("Enter new index number that you would like to change to");
							while(!sc.hasNextInt()) {
								System.out.println("Enter new index number that you would like to change to");
								System.out.println("  Please enter integer only");
								sc.next();
							}
							int newindexNo = sc.nextInt();
							
							if(curindexNo==newindexNo) {
								System.out.println("You have entered the same index\n");
								break;
							}
							
							String curcourseCode = courseMgr.getCourseCodeByIndex(curindexNo);
							String newcourseCode = courseMgr.getCourseCodeByIndex(newindexNo);
							
							if ((courseMgr.checkVacancyForIndexWithoutDisplay(newindexNo)<0)||!(curcourseCode.equals(newcourseCode))) {
								System.out.println("Invalid index number");
								break;
							}
							else if((courseMgr.checkVacancyForIndexWithoutDisplay(newindexNo)==0)){
								System.out.println(newindexNo + " has no vacancy");
								break;
							}
		
							ArrayList<ArrayList<String>> curCourseInfo = courseMgr.getCourseInfosByIndex(curindexNo);
							System.out.println("\nCourse Code : "+curcourseCode);
							System.out.println("\nCurrent Index : "+curindexNo);
							for(int i=0;i<curCourseInfo.size();i++) {
								curCourseInfo.get(i).set(2, DayOfWeek.of(Integer.parseInt(curCourseInfo.get(i).get(2))).toString());
							}
							System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
							for(int i=0;i<curCourseInfo.size();i++) {
								for(int j=0;j<curCourseInfo.get(i).size();j++) {
									System.out.print(curCourseInfo.get(i).get(j)+"|");
								}
								System.out.print("\n");
							}
							System.out.print("\n");
							ArrayList<ArrayList<String>> newCourseInfo = courseMgr.getCourseInfosByIndex(newindexNo);
							System.out.println("\nNew Index : "+newindexNo);
							for(int i=0;i<newCourseInfo.size();i++) {
								newCourseInfo.get(i).set(2, DayOfWeek.of(Integer.parseInt(newCourseInfo.get(i).get(2))).toString());
							}
							System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
							for(int i=0;i<newCourseInfo.size();i++) {
								for(int j=0;j<newCourseInfo.get(i).size();j++) {
									System.out.print(newCourseInfo.get(i).get(j)+"|");
								}
								System.out.print("\n");
							}
							System.out.print("\n");
							
							
							
							char confirm1='?';
							do {
								System.out.println("Confirm? Y/N");
								confirm1 = sc.next().charAt(0);
							}while(!(confirm1=='Y'||confirm1=='N'));
							if(Character.compare(confirm1, 'N')==0) {
								System.out.println("Cancelled");
								break;
							}
							
							ArrayList<ArrayList<String>> timeslots= studentCourseMgr.findStudentAllCourse(loginID);
							ArrayList<ArrayList<ArrayList<String>>> alltocheck = new ArrayList<ArrayList<ArrayList<String>>>();
							
							for (int i = 0; i < timeslots.size(); i++) {
								if (Integer.parseInt(timeslots.get(i).get(1) )== curindexNo ) {
									timeslots.remove(i);
								}
							}
							for(int i=0;i<timeslots.size();i++) {
								alltocheck.add(courseMgr.getAllTime(timeslots.get(i).get(0), Integer.parseInt(timeslots.get(i).get(1))));
							}
							Boolean clash=false;
							for(int i=0;i<alltocheck.size();i++) {
								for(int j=0;j<alltocheck.get(i).size();j++) {
									if(collision(curcourseCode, newindexNo,  Integer.parseInt(alltocheck.get(i).get(j).get(0)), 
											Time.valueOf(alltocheck.get(i).get(j).get(1)), Time.valueOf(alltocheck.get(i).get(j).get(2)), 
											Boolean.parseBoolean(alltocheck.get(i).get(j).get(3)), Boolean.parseBoolean(alltocheck.get(i).get(j).get(4)),courseMgr)) {
										System.out.println("There are clashing time slots \n");
										clash = true;
										break;
									}
								}
							}
							if(clash) {
								break;
							}
							//add new index record into student_course: done
							//delete old index from student_course: done
							//old index slot +1 done
							//new index slot -1 done
							
							ArrayList<String> writeNewIndexData = new ArrayList<String>();
							ArrayList<String> writeDeleteIndexData = new ArrayList<String>();
							writeDeleteIndexData = studentCourseMgr.dropCourseS(loginID, curcourseCode);
							courseMgr.reduceIndexBy1withoutdisplay(curcourseCode, curindexNo, false);
							writeNewIndexData = studentCourseMgr.registerCourseS(studentMgr.findStudentwithMat(loginID), loginID, curcourseCode, newindexNo, courseMgr.getAUByIndex(newindexNo));
							courseMgr.reduceIndexBy1withoutdisplay(curcourseCode, newindexNo, true);
							

							
							try {
								FileHandler.writeFileDelerow(readStudCourseFile, writeDeleteIndexData);
								FileHandler.writeFile1(readStudCourseFile,writeNewIndexData);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("You have successfully change "+ curcourseCode+" index: "+curindexNo+" into "+newindexNo+".\n");		
							break;
							
						}
							
							
						case 6: {
							//stu1 keyin his index number
								//if it is a true index
								//if his index
							//key in stu2 mat no
								//check if stu2 exist
								//exist :passwd(hash)
							//key stu2 indexnum
								//if it is a true index
								//if his index
								//if same course
								//stu1 new clash with stu2 new clash
							//delete stu1 record
							//delete stu2 record
							
							//add stu2 record
							//add stu1 record
							
							//success
							System.out.println("Enter the index number that you would like to swap with student 2");
							while(!sc.hasNextInt()) {
								
								System.out.println("  Please enter integer only");
								System.out.println("Enter the index number that you would like to swap with student 2");
								sc.next();
							}
							
							int stu1indexNo = sc.nextInt();
							if ((courseMgr.checkVacancyForIndexWithoutDisplay(stu1indexNo)<0)) {
								System.out.println("Invalid index number");
								break;
							}
							else if(!(studentCourseMgr.isRegisteredIndex(loginID, stu1indexNo))) {
								System.out.println("You are not registered for this index");
								break;
							}
							
							System.out.print("Enter student2 LoginID (matric number): ");
							String loginID2 = sc.next();
							
							System.out.print("\nEnter student2 password: ");
							String passwd2 = sc.next();
							
							if(Character.compare((userloginmgr.validateLogin(loginID2, passwd2)), 'S')!=0) {
								System.out.println("Invalid Login");
								break;
								
							}
							
							System.out.println("Enter student 2 index number that you would like to swap");
							
							while(!sc.hasNextInt()) {
								System.out.println("Enter student 2 index number that you would like to swap");
								System.out.println("  Please enter integer only");
								sc.next();
							}
							int stu2indexNo = sc.nextInt();
							
							if ((courseMgr.checkVacancyForIndexWithoutDisplay(stu2indexNo)<0)) {
								System.out.println("Invalid index number");
								break;
							}
							else if(!(studentCourseMgr.isRegisteredIndex(loginID2, stu2indexNo))) {
								System.out.println("Student 2 is not registered for this index");
								break;
							}
							
							String stu1courseCode = courseMgr.getCourseCodeByIndex(stu1indexNo);
							String stu2courseCode = courseMgr.getCourseCodeByIndex(stu2indexNo);
							
							if (!(stu1courseCode.equals(stu2courseCode))) {
								System.out.println("Invalid index number, different Course");
								break;
							}
							
							
									
							ArrayList<ArrayList<String>> stu1CourseInfo = courseMgr.getCourseInfosByIndex(stu1indexNo);
							
							System.out.println("\nCourse Code : "+stu1courseCode);
							System.out.println("Student #1");
							System.out.println("Matric: "+loginID+"\t Index Number1: "+stu1indexNo);
							for(int i=0;i<stu1CourseInfo.size();i++) {
								stu1CourseInfo.get(i).set(2, DayOfWeek.of(Integer.parseInt(stu1CourseInfo.get(i).get(2))).toString());
							}
							System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
							for(int i=0;i<stu1CourseInfo.size();i++) {
								for(int j=0;j<stu1CourseInfo.get(i).size();j++) {
									System.out.print(stu1CourseInfo.get(i).get(j)+"|");
								}
								System.out.print("\n");
							}
							System.out.print("\n");
							
							ArrayList<ArrayList<String>> stu2CourseInfo = courseMgr.getCourseInfosByIndex(stu2indexNo);
							
							System.out.println("Student #2");
							System.out.println("Matric: "+loginID2+"\t Index Number1: "+stu2indexNo);
							for(int i=0;i<stu2CourseInfo.size();i++) {
								stu2CourseInfo.get(i).set(2, DayOfWeek.of(Integer.parseInt(stu2CourseInfo.get(i).get(2))).toString());
							}
							System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
							for(int i=0;i<stu2CourseInfo.size();i++) {
								for(int j=0;j<stu2CourseInfo.get(i).size();j++) {
									System.out.print(stu2CourseInfo.get(i).get(j)+"|");
								}
								System.out.print("\n");
							}
							System.out.print("\n");
					
							
							
							char confirm1='?';
							do {
								System.out.println("Confirm? Y/N");
								confirm1 = sc.next().charAt(0);
							}while(!(confirm1=='Y'||confirm1=='N'));
							if(Character.compare(confirm1, 'N')==0) {
								System.out.println("Cancalled");
								break;
							}
							
							
							ArrayList<ArrayList<String>> timeslots1= studentCourseMgr.findStudentAllCourse(loginID);
							ArrayList<ArrayList<String>> timeslots2= studentCourseMgr.findStudentAllCourse(loginID2);
							ArrayList<ArrayList<ArrayList<String>>> alltocheck = new ArrayList<ArrayList<ArrayList<String>>>();
							ArrayList<ArrayList<ArrayList<String>>> alltocheck2 = new ArrayList<ArrayList<ArrayList<String>>>();
							
							for (int i = 0; i < timeslots1.size(); i++) {
								if (Integer.parseInt(timeslots1.get(i).get(1))== stu1indexNo ) {
									timeslots1.remove(i);
								}
							}
							for (int i = 0; i < timeslots2.size(); i++) {
								if (Integer.parseInt(timeslots2.get(i).get(1))== stu2indexNo ) {
									timeslots2.remove(i);
								}
							}
							for(int i=0;i<timeslots1.size();i++) {
								alltocheck.add(courseMgr.getAllTime(timeslots1.get(i).get(0), Integer.parseInt(timeslots1.get(i).get(1))));
							}
							for(int i=0;i<timeslots2.size();i++) {
								alltocheck2.add(courseMgr.getAllTime(timeslots2.get(i).get(0), Integer.parseInt(timeslots2.get(i).get(1))));
							}
							Boolean clash=false;
							for(int i=0;i<alltocheck.size();i++) {
								for(int j=0;j<alltocheck.get(i).size();j++) {
									if(collision(stu1courseCode, stu2indexNo,  Integer.parseInt(alltocheck.get(i).get(j).get(0)), 
											Time.valueOf(alltocheck.get(i).get(j).get(1)), Time.valueOf(alltocheck.get(i).get(j).get(2)), 
											Boolean.parseBoolean(alltocheck.get(i).get(j).get(3)), Boolean.parseBoolean(alltocheck.get(i).get(j).get(4)),courseMgr)) {
										System.out.println("There are clashing time slots \n");
										clash = true;
										break;
									}
								}
							}
							for(int i=0;i<alltocheck2.size();i++) {
								for(int j=0;j<alltocheck2.get(i).size();j++) {
									if(collision(stu1courseCode, stu1indexNo,  Integer.parseInt(alltocheck2.get(i).get(j).get(0)), 
											Time.valueOf(alltocheck2.get(i).get(j).get(1)), Time.valueOf(alltocheck2.get(i).get(j).get(2)), 
											Boolean.parseBoolean(alltocheck2.get(i).get(j).get(3)), Boolean.parseBoolean(alltocheck2.get(i).get(j).get(4)),courseMgr)) {
										System.out.println("There are clashing time slots \n");
										clash = true;
										break;
									}
								}
							}
							if(clash) {
								break;
							}
							
							//stu1 new clash with stu2 new clash
							//delete stu1 record
							//delete stu2 recor
							
							//add stu2 record
							//add stu1 recor
							
							//success
							ArrayList<String> writeNewIndexData1 = new ArrayList<String>();
							ArrayList<String> writeNewIndexData2 = new ArrayList<String>();
							ArrayList<String> writeDeleteIndexData1 = new ArrayList<String>();
							ArrayList<String> writeDeleteIndexData2 = new ArrayList<String>();
							
							writeDeleteIndexData1 = studentCourseMgr.dropCourseS(loginID, stu1courseCode);
							writeDeleteIndexData2 = studentCourseMgr.dropCourseS(loginID2, stu2courseCode);
							
							writeNewIndexData1 = studentCourseMgr.registerCourseS(studentMgr.findStudentwithMat(loginID), loginID, stu1courseCode, stu2indexNo, courseMgr.getAUByIndex(stu2indexNo));
							
							writeNewIndexData2 = studentCourseMgr.registerCourseS(studentMgr.findStudentwithMat(loginID2), loginID2, stu2courseCode, stu1indexNo, courseMgr.getAUByIndex(stu1indexNo));

							
							try {
								FileHandler.writeFileDelerow(readStudCourseFile, writeDeleteIndexData1);
								FileHandler.writeFileDelerow(readStudCourseFile, writeDeleteIndexData2);
								FileHandler.writeFile1(readStudCourseFile,writeNewIndexData1);
								FileHandler.writeFile1(readStudCourseFile,writeNewIndexData2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							System.out.println(loginID+" -Index Number "+stu1indexNo+" has been successfully swapped with "+loginID2 +" -Index Number "+stu2indexNo);
							System.out.println();		
							break;
							}
						case 7: {
							ArrayList<ArrayList<String>> timetable = studentCourseMgr.findStudentAllCourse(loginID);
							ArrayList<ArrayList<String>> courseInfo = new ArrayList<ArrayList<String>>();
							for (int i = 0; i < timetable.size(); i++) {
								courseInfo = courseMgr.getCourseInfosByIndex(Integer.parseInt(timetable.get(i).get(1)));
								System.out.println("\nCourseCode : "+timetable.get(i).get(0));
								System.out.println("ClassType|Group|Day|Time|Venue|Remarks");
								for(int j=0;j<courseInfo.size();j++) {
									courseInfo.get(j).set(2, DayOfWeek.of(Integer.parseInt(courseInfo.get(j).get(2))).toString());
								}
								for(int j=0;j<courseInfo.size();j++) {
									for(int k=0;k<courseInfo.get(j).size();k++) {
										System.out.print(courseInfo.get(j).get(k)+"|");
									}
									System.out.print("\n");
								}
								System.out.print("\n");
							}
							break;
						}
						}
						
					}while(choice!=8);
						break;}
				default:
					System.out.print("Invalid Login! Please re-enter.\n");
					break;
			}
		}while(!(loginID.equals("exit")));
	}
    public static boolean collision(String courseCode, int indexNum,int day, Time startTime, Time endTime, boolean isLab, boolean labisOdd,CourseMgr courseMgr) {
   
		long startint2=startTime.getTime();
		long endint2=endTime.getTime();	
		ArrayList<ArrayList<String>> listofTime = courseMgr.getAllTime(courseCode, indexNum);
		if(listofTime==null){
			System.out.print("No such index or course!");
			return false;
		}
		if(!isLab){
			for(int i=0;i<3;i++) {
				if(day==Integer.parseInt(listofTime.get(i).get(0))) {
					long startint1=Time.valueOf(listofTime.get(i).get(1)).getTime();
					long endint1=Time.valueOf(listofTime.get(i).get(2)).getTime();
					if(endint1==startint2||endint2==startint1) {
						
					}else if(startint2<=endint1 && startint2 >= startint1) {
						return true;
					}else if(startint1<=endint2 && startint1 >= startint2){
						return true;
					}
				}
				
			}
		}else {
			for(int i=0;i<2;i++) {
				if(day==Integer.parseInt(listofTime.get(i).get(0))) {
					long startint1=Time.valueOf(listofTime.get(i).get(1)).getTime();
					long endint1=Time.valueOf(listofTime.get(i).get(2)).getTime();
					if(endint1==startint2||endint2==startint1) {
						
					}else if(startint2<=endint1 && startint2 >= startint1) {
						return true;
					}else if(startint1<=endint2 && startint1 >= startint2){
						return true;
					}
				}
			}
			if(!(labisOdd^Boolean.parseBoolean(listofTime.get(2).get(4)))) {
				long startint1=Time.valueOf(listofTime.get(2).get(1)).getTime();
				long endint1=Time.valueOf(listofTime.get(2).get(2)).getTime();
				if(endint1==startint2||endint2==startint1) {
					
				}else if(startint2<=endint1 && startint2 >= startint1) {
					return true;
				}else if(startint1<=endint2 && startint1 >= startint2){
					return true;
				}
			}
		}
		return false;
	}
    

}
