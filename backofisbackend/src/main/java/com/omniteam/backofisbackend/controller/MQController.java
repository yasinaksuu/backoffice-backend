package com.omniteam.backofisbackend.controller;

import com.omniteam.backofisbackend.jms.EmailPublisherMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mq")
public class MQController {



    @Autowired
    EmailPublisherMQ publisherMQ;

    @GetMapping
    public ResponseEntity runMQTest()
    {
        publisherMQ.sendToEmailQueue(new String("asdadasdas"));
        return ResponseEntity.ok().build();
    }


}
