package cz2002assignment;

public class context {
	private Notification notification;
	
	public context(Notification notification) {
		this.notification=notification;
	}
	
	public void executeStrategy(String to, String subject, String body){
	      notification.sendNotification(to, subject, body);
	   }

}
