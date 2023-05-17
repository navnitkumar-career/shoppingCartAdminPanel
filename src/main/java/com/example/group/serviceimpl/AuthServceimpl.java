package com.example.group.serviceimpl;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.group.Entity.User;
import com.example.group.repository.UserRepository;
import com.example.group.service.AuthService;

@Service
public class AuthServceimpl implements AuthService {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	@Autowired
	UserRepository userrepository;

	@Override
	public Boolean auth(String email, String password) {
		User user = userrepository.findByEmail(email);
		if (user == null) {
			return false;
		} else if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Boolean userRegister(String email, String password, String name, String repeatpassword) {
		User findByEmail = userrepository.findByEmail(email);
		if (password.contentEquals(repeatpassword) && findByEmail == null) {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			String token = getToken();
			user.setToken(token);
			userrepository.save(user);
			return true;
		} else {
			return false;
		}
	}

	private String getToken() {
		String token = auth();
		User findByToken = userrepository.findByToken(token);
		if (findByToken != null) {
			getToken();
		}
		return token;
	}

	@Override
	public boolean forgotPassword(String email, String cpassword, String npassword, String nrpassword) {
		User dEmail = userrepository.findByEmail(email);

		if (dEmail == null) {
			return false;
		} else if (dEmail.getPassword().contains(cpassword) && npassword.contentEquals(nrpassword)) {
			dEmail.setPassword(npassword);
			userrepository.save(dEmail);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean sendEmail(String email) {
		String subject = "Opsnece Technologies";
		String from = "abc@gmail.com";
		String to = email;
		User user = userrepository.findByEmail(email);
		try {
		String token = user.getToken();
		
			sendEmail1(from, to, subject, token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void changePassword(String token, String repeatpassword, String password) {
		User user = userrepository.findByToken(token);
		user.setPassword(password);
		userrepository.save(user);
	}

	void sendEmail1(String from, String to, String subject, String token) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);

		Session session = Session.getDefaultInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "xxxxxxxxxxxx");
			}
		});
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(from);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			String html = "<a href='http://localhost:8087/email/" + token + "'>Click Me.........</a>";
			msg.setText(html, "UTF-8", "html");
			Transport.send(msg);
			System.out.println("sent success........");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public String auth() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		base64Encoder.encodeToString(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

}
