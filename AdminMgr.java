package cz2002assignment;

import java.util.ArrayList;

public class AdminMgr {
	private ArrayList<Admin> adminList = new ArrayList<Admin>();
	
	public void addAdmin(String loginID, String psword) {
		adminList.add(new Admin());
		adminList.get(adminList.size()-1).setLoginID(loginID);
	}

	public void deleteAdmin(String loginID) {
		for (int i = 0; i < adminList.size(); i++) {
			if (adminList.get(i).getLoginID().equals(loginID)) {
				adminList.remove(i);
				System.out.println("Successfully deleted admin "+ loginID);
				break;
			}
		}

	}
}
