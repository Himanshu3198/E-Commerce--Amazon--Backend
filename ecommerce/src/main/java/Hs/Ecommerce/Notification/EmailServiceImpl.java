package Hs.Ecommerce.Notification;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {


    public static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private JavaMailSender  mailSender;

    @Override
    public void sendSimpleEmail(String to,String subject, String body){
        try{
            LOGGER.info("Drafting new Email!");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            LOGGER.info("Email has been sent to user: {}",to);

        }catch (MailSendException mae){
            LOGGER.error("An error occurred while sending email {}",mae.getMessage());
            throw new MailSendException("An error Occurred while sending email: "+mae);
        }catch (Exception ex){
            LOGGER.error("An error occurred : {}",ex.getMessage());
            throw new RuntimeException("An error Occurred while sending email: "+ex);
        }

    }
}
