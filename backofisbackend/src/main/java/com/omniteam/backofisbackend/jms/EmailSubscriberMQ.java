package com.omniteam.backofisbackend.jms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailSubscriberMQ {


    @JmsListener(destination = "EmailQueue")
    public void receiveMessage(Object messageObject)
    {
        System.out.println(messageObject);
    }

}
