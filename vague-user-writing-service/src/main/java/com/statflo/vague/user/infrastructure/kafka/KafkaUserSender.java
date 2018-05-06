/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.infrastructure.kafka;

import com.statflo.vague.user.event.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class KafkaUserSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(UserCreatedEvent event) {
        kafkaTemplate.send("users", event.toJSON());
    }
}
