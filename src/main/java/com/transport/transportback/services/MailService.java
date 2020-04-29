package com.transport.transportback.services;


import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Service
//@Slf4j
public class MailService {


    @Value("${mail.auth}")
    private String maileAuth;

    @Value("${mail.starttls.enable}")
    private String mailStarttlsEnable;

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private String mailPort;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;



    @Async
    /**
     * @Author Ahmed chega
     * Send email
     */
    public void sendEmail(String from, String to, String subject, String content, File fileAttachment, String fileName, boolean isSimpleMail) {

          to =  mailUsername;

        if (StringUtils.isBlank(from)) {
            throw new NullArgumentException("from");
        }
        if (StringUtils.isBlank(to)) {
            throw new NullArgumentException("to");
        }
        if (StringUtils.isBlank(subject)) {
            throw new NullArgumentException("subject");
        }
        if (StringUtils.isBlank(content)) {
            throw new NullArgumentException("content");
        }

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.auth", maileAuth);
        properties.setProperty("mail.smtp.starttls.enable", mailStarttlsEnable);
        properties.setProperty("mail.smtp.host", mailHost);
        properties.setProperty("mail.smtp.port", mailPort);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Send the actual HTML message, as big as you like
            message.setContent("<div>Cette message à été envoyer par cette Email : "+from+" <br/> Message : "+content+"</div>", "text/html");
            System.out.println("subject : "+subject+" content : "+content+" to : "+to);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }




    }


}
