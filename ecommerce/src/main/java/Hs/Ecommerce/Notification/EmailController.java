package Hs.Ecommerce.Notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/email")
public class EmailController {


    @Autowired
    private IEmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String to,@RequestParam String subject, @RequestParam String body){
        emailService.sendSimpleEmail(to,subject,body);
        return ResponseEntity.ok("Email has been sent to: "+to);
    }
}
