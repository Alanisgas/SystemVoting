package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.EmailRequest;
import ar.edu.unaj.login.model.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    //@app.sendgrid.templateIdValue("${}")
    private  String templeteId;
    //@Value("${app.sendgrid.key}")
    private String sendGridApiKey;
    @Autowired
    Environment env;
    public Response sendTextEmail(EmailRequest emailRequest) {
        User user= new User();
        Email from = new Email(user.getEmail());

        String subject = emailRequest.getSubject();
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/html", emailRequest.getBody());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(env.getProperty(sendGridApiKey));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response;
        } catch (IOException ex) {
            return null;
        }
    }

    public  Mail prepareMail(String email){
        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail("gaston.alanis@gmail.com");
        Email to = new Email();
        to.setEmail(email);
        Personalization personalization = new Personalization();
        mail.setTemplateId(templeteId);
        return  mail;
    }
}
