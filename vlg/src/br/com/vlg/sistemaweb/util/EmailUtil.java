package br.com.vlg.sistemaweb.util;

import java.util.Date;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public static void sendMail(String to, String subject, String body)
			throws MessagingException {

		Authenticator authenticator = getAuthenticator();
		InternetAddress mailFrom = new InternetAddress(getFrom());
		InternetAddress mailTo = new InternetAddress(to);
		Properties properties = getProperties();

		Session session = Session.getDefaultInstance(properties, authenticator);

		Message msg = new MimeMessage(session);

		msg.setFrom(mailFrom);
		msg.setSentDate(new Date());

		msg.setRecipient(Message.RecipientType.TO, mailTo);
		msg.setRecipient(Message.RecipientType.CC, mailFrom);
		msg.setSubject(subject);

		msg.setText(body);

		Transport.send(msg);

	}

	private static Properties getProperties() {

		ExternalContext context = getContext();

		Properties properties = new Properties();

		properties.put("mail.smtp.host", context.getInitParameter("servidor"));
		properties.put("mail.smtp.port", context.getInitParameter("porta"));
		properties.put("mail.smtp.auth", context.getInitParameter("auth"));
		properties.put("mail.smtp.starttls.enable",
				context.getInitParameter("tls"));

		return properties;

	}

	private static ExternalContext getContext() {

		return FacesContext.getCurrentInstance().getExternalContext();
	}

	private static String getFrom() {

		ExternalContext context = getContext();

		return context.getInitParameter("usuario");
	}

	private static Authenticator getAuthenticator() {

		final ExternalContext context = getContext();

		return new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						context.getInitParameter("usuario"),
						context.getInitParameter("senha"));
			}

		};

	}

}
