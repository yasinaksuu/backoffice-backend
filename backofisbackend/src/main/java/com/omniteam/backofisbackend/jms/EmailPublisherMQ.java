package com.omniteam.backofisbackend.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailPublisherMQ {


    @Autowired
    JmsTemplate jmsTemplate;

    public void sendToEmailQueue(Object emailObject) {
        jmsTemplate.convertAndSend(emailObject);

    }


}
