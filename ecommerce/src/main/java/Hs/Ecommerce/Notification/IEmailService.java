package Hs.Ecommerce.Notification;

public interface IEmailService {

    void sendSimpleEmail(String to,String subject, String body);
}
