package cz2002assignment;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserLogin{
    private String UserID;
    private String passwd;
    private char roletype;
    private String algorithm = "SHA3-256";

    public UserLogin(String UserID, String passwd, char roletype){
        this.UserID = UserID;
        this.passwd = passwd;
        this.roletype = roletype;
    }
    
    public UserLogin() {
    }

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswdWithHash(String passwd) {
		byte[] shaInBytes = UserLogin.digest(passwd.getBytes(UTF_8), algorithm);
		this.passwd = bytesToHex(shaInBytes);
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public char getRoletype() {
		return roletype;
	}

	public void setRoletype(char roletype) {
		this.roletype = roletype;
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
}