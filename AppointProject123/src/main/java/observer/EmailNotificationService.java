package observer;

import java.util.Properties;
import java.util.logging.Logger; 
import java.util.logging.Level;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailNotificationService implements Observer {

 
    private static final String SENDER_EMAIL = "s12323530@stu.najah.edu";
    private static final String APP_PASSWORD = System.getenv("SECRET");
    private static final String RECEIVER_EMAIL = "s12323530@stu.najah.edu";
    
    private static final Logger LOGGER = Logger.getLogger(EmailNotificationService.class.getName());

    @Override
    public void update(String message) {
        sendEmail(RECEIVER_EMAIL, "Appointment Notification", message);
    }

    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            
                return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(SENDER_EMAIL));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);
            emailMessage.setText(body);

            Transport.send(emailMessage);
            LOGGER.info("Real email sent successfully."); 

        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Failed to send email", e);
        }
    }
}
