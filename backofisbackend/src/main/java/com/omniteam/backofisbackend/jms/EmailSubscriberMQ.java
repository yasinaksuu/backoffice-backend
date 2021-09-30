package com.omniteam.backofisbackend.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class EmailSubscriberMQ {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JavaMailSender emailSender;

    @JmsListener(destination = "EmailQueue")
    public void receive(TextMessage messageObject)
    {
        try {

            EmailMessage emailMessage = objectMapper.readValue(messageObject.getText(),EmailMessage.class);
            System.out.println(emailMessage);
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(emailMessage.getFrom());
            mail.setTo(emailMessage.getTo());
            mail.setSubject("Test Mail Subject");
            mail.setText(emailMessage.getMessage());
            emailSender.send(mail);
            messageObject.acknowledge();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        catch (MailException ex)
        {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
