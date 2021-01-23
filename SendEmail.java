package cz2002assignment;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail implements Notification{
	final String username = "2002oodpTest@gmail.com";
   	final String password = "abcTest123!";
    //private static SendEmail instance = new SendEmail();

    public void sendNotification(String to, String subject, String body){
    	Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

        try{
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            //Transport transport = session.getTransport("smtp");
            Transport.send(message);
            //transport.close();
        } catch (MessagingException me){
             me.printStackTrace();
        }
    }
}
