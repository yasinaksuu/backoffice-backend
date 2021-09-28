package com.omniteam.backofisbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AMQPService {
    void sendSystemEmail(String to,String message) throws JsonProcessingException;
}
