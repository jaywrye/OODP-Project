package cz2002assignment;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

	public class UserLoginMgr {
		
		private ArrayList<UserLogin> userLoginList = new ArrayList<UserLogin>();
	
	    private String algorithm = "SHA3-256";
	    
		public void editUser(String userID, String newPsword) {
			for (int i = 0; i < userLoginList.size(); i++) {
				if (userLoginList.get(i).getUserID()==userID) {
					userLoginList.get(i).setPasswd(newPsword);
					System.out.println("You have successfully changed your password!");
					break;
				}
			}
		}
		public void addUser(String userID, String psword, char R) {
			userLoginList.add(new UserLogin());
			userLoginList.get(userLoginList.size()-1).setUserID(userID);
			userLoginList.get(userLoginList.size()-1).setPasswd(psword);
			userLoginList.get(userLoginList.size()-1).setRoletype(R);
		}
		
		public void addUserWithHash(String userID, String psword, char R) {
			userLoginList.add(new UserLogin());
			userLoginList.get(userLoginList.size()-1).setUserID(userID);
			userLoginList.get(userLoginList.size()-1).setPasswdWithHash(psword);
			userLoginList.get(userLoginList.size()-1).setRoletype(R);
		}

		public void deleteUser(String userID) {
			for (int i = 0; i < userLoginList.size(); i++) {
				if (userLoginList.get(i).getUserID()==userID) {
					userLoginList.remove(i);
					System.out.println("Successfully deleted admin "+ userID);
					break;
				}
			}

		}
		public ArrayList<UserLogin> getUserLoginList() {
			return userLoginList;
		}
		
		public char validateLogin(String logiId,String passwd) {
			int i;
			
			for(i=0;i<userLoginList.size();i++) {
				if(logiId.equals(userLoginList.get(i).getUserID())) {
					
					byte[] shaInBytes = UserLogin.digest(passwd.getBytes(UTF_8), algorithm);
			        String codedpassword = bytesToHex(shaInBytes);
					if (codedpassword.equals(userLoginList.get(i).getPasswd())) {
						char role = userLoginList.get(i).getRoletype();
						return role;
						
					}
					else {
						return '~';
					}
				}
				
			}
			return '?';
		}
		private static final Charset UTF_8 = StandardCharsets.UTF_8;

	    public static byte[] digest(byte[] input, String algorithm) {
	        MessageDigest md;
	        try {
	            md = MessageDigest.getInstance(algorithm);
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalArgumentException(e);
	        }
	        byte[] result = md.digest(input);
	        return result;
	    }

	    public static String bytesToHex(byte[] bytes) {
	        StringBuilder sb = new StringBuilder();
	        for (byte b : bytes) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    }
	    
	    public String getpassword(String loginID) {
	    	for (int i = 0; i < userLoginList.size(); i++) {
				if(userLoginList.get(i).getUserID().equals(loginID)) {
					return userLoginList.get(i).getPasswd();
				}
			}
	    	return null;
	    }
}
