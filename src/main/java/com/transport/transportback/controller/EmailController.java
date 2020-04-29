package com.transport.transportback.controller;

import com.transport.transportback.dto.EmailDTO;
import com.transport.transportback.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/mail/")
@Slf4j
public class EmailController {


    @Autowired
    private MailService mailService;



    @PostMapping(value = "/sendemail")
    public Boolean sendEmail(@RequestBody EmailDTO emailForm) {

        System.out.println("here  sendEmail");
        System.out.println("here  getContactEmail : "+emailForm.getContactEmail()+" getContactSubject: "+emailForm.getContactSubject()+" getContactMessage: "+emailForm.getContactMessage());
        if(emailForm.getContactEmail().isEmpty() || emailForm.getContactSubject().isEmpty() || emailForm.getContactMessage().isEmpty()) {
            System.err.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            log.error("unable to obtain contact form data");
            return false;
        }

        mailService.sendEmail(emailForm.getContactEmail(),null,emailForm.getContactSubject(),emailForm.getContactMessage(),null,null,true);
        return true;
    }

    @GetMapping(value = "/test")
    public  String test(){
        System.out.println("Hello world");
        return "Hello world";
    }

}
