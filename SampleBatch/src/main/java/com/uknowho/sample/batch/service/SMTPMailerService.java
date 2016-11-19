package com.uknowho.sample.batch.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.uknowho.sample.batch.xmlmodel.NotificationEmailModel;

/**
 * This SMTPMailerService class implements Java Mail service.
 * 
 * Created date <22-Mar-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service("smtpmailService")
public class SMTPMailerService {

	private static final Logger logger = LoggerFactory.getLogger(SMTPMailerService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	public SMTPMailerService() {
		logger.info("SMTPMailerService default constrction method load.");
	}
	
	public boolean sendSimpleMail(
			final String from, 
			final String to, 
			final String subject,
			final String body) {
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		
		return sendMail(simpleMailMessage);
	}
	
	public boolean sendSimpleMail(final NotificationEmailModel email) {
		return sendSimpleMail(
				email.getFromEmail(), 
				email.getToEmail(), 
				email.getSubject(), 
				email.getContent());
	}
	
	public void sendMimeMail(final NotificationEmailModel model) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(model.getFromEmail());
		helper.setTo(model.getToEmail());

		// use the true flag to indicate the text included is HTML
		// TODO use velocity to general content 
		helper.setText(model.getContent(), true);
		helper.setSubject(model.getSubject());
		
		sendMail(message);
	}
	
	public boolean sendMail(final SimpleMailMessage mailmessage) {
		boolean emailsent = false;
		try {
			javaMailSender.send(mailmessage);
			emailsent = true;
			
		} catch (MailParseException e) {
			logger.error(e.toString());
		} catch (MailAuthenticationException e) {
			logger.error(e.toString());
		} catch (MailSendException e) {
			logger.error(e.toString());
		} catch (MailException e) {
			logger.error(e.toString());
		}
		return emailsent;
	}

	public boolean sendMail(final MimeMessage mimemessage) {
		boolean emailsent = false;
		try {
			javaMailSender.send(mimemessage);
			emailsent = true;
			
		} catch (MailParseException e) {
			logger.error(e.toString());
		} catch (MailAuthenticationException e) {
			logger.error(e.toString());
		} catch (MailSendException e) {
			logger.error(e.toString());
		} catch (MailException e) {
			logger.error(e.toString());
		}
		return emailsent;
	}
	
	public boolean sendMail(final MimeMessagePreparator mimemessage) {
		boolean emailsent = false;
		try {
			javaMailSender.send(mimemessage);
			emailsent = true;
			
		} catch (MailParseException e) {
			logger.error(e.toString());
		} catch (MailAuthenticationException e) {
			logger.error(e.toString());
		} catch (MailSendException e) {
			logger.error(e.toString());
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return emailsent;
	}
	
}
