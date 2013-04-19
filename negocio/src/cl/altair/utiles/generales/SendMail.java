package cl.altair.utiles.generales;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void sendMail(String to, String mensaje, String subject) throws UnsupportedEncodingException, MessagingException {
		try {
			//Configuracion de las propiedades para el envio de correos via gmail
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.user", "lenpino");
			properties.put("mail.smtp.password", "m13d2n");
			properties.put("mail.smtp.from", "lenpino@gmail.com");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.debug", "true");
			properties.put("mail.smtp.socketFactory.fallback", "false");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			SMTPAuthenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(properties, auth);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(mensaje);
			msg.setSubject(new String(subject.getBytes(), "iso-8859-1"), "iso-8859-1");
			msg.setFrom(new InternetAddress(properties.getProperty("mail.smtp.from")));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			Transport transport = session.getTransport("smtps");
			transport.connect(properties.getProperty("mail.smtp.host"),
					465,
					"lenpino",
					"password");
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
}

}
