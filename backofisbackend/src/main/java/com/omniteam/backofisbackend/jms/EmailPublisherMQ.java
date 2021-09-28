package com.omniteam.backofisbackend.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailPublisherMQ {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void send(String from, String to, String message) throws JsonProcessingException {

        EmailMessage emailMessage = EmailMessage.builder()
                .from(from)
                .to(to)
                .message(message)
                .build();

        jmsTemplate.convertAndSend("EmailQueue", objectMapper.writeValueAsString(emailMessage));

    }


}
