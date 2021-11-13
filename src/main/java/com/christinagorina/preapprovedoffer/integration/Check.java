package com.christinagorina.preapprovedoffer.integration;

import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;

@MessagingGateway
public interface Check {

    @Gateway(requestChannel = "checkChannel", replyChannel = "decisionChannel")
    String process(List<CheckMessage> checkMessage);
}
