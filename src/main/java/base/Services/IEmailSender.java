package base.Services;

public interface IEmailSender {

	void sendEmailTo(String to, String subject, String text);

}