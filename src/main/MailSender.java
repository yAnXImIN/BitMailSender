package main;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
	private static String Subject = "BitAutoMail";
	public static void main(String[] args) throws Exception {
		String username = args[0];
		String password = args[1];
		String toMailAdress = args[2];
		
		Properties prop = new Properties();
		prop.setProperty("mail.host", "mail.bit.edu.cn");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.port", "25");
		prop.setProperty("mail.smtp.auth", "true");
		
		MyAuthenticator myAuthenticator = new MyAuthenticator(username, password);
		Session session = Session.getInstance(prop,myAuthenticator);
		session.setDebug(false);

		Message message = createSimpleMessage(session,username,toMailAdress);
		Transport.send(message);
	
	}
	private static MimeMessage createSimpleMessage(Session session,String fromEmail,String toEmail) throws Exception{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(Subject);
		
		message.setContent(getInfo(), "text/html;charset=UTF-8");
		return message;
	}
	private static String getInfo() throws Exception {
		StringBuffer info = new StringBuffer();
		//TODO 添加你要在邮件里获取的信息

		return info.toString();
	}
	
}

class MyAuthenticator extends Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}