package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.dto.EmailRequest;
import ar.edu.unaj.login.service.EmailService;
import com.sendgrid.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/email")
@AllArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequest emailRequest){
        Response response = emailService.sendTextEmail(emailRequest);
        if(response.getStatusCode() == 200 || response.getStatusCode() == 202){
            return new ResponseEntity<>("Sent successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to send", HttpStatus.BAD_REQUEST);
    }



}
