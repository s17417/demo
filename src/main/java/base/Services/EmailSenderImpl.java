package base.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import base.Utils.Security.Filters.JwtAuthenticationFilter;

@Async
@Component
public class EmailSenderImpl implements IEmailSender {
	
	@Autowired
    private JavaMailSender emailSender;
	
	private Logger logger=LoggerFactory.getLogger(EmailSenderImpl.class);
	
	
	@Override
	public void sendEmailTo(String to, String subject, String text)  {
		try {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("tomasz.polawski.serv@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
		} catch (MailException ex) {
			logger.error(ex.getClass().getSimpleName()+": "+ex.getMessage());
		}
	}

}
