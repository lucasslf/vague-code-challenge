/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.infrastructure.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.statflo.vague.user.domain.UserService;
import com.statflo.vague.user.event.UserCreatedEvent;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class KafkaConsumer {

    @Autowired
    UserService userService;

    static String USER_CREATED_EVENT = "UserCreated";

    @KafkaListener(topics = "users", id = "users")
    public void listen(@Payload String event) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(event, new TypeReference<Map<String, String>>() {
        });
        if (USER_CREATED_EVENT.equals(map.get("event"))) {
            UserCreatedEvent userCreatedEvent = new UserCreatedEvent(UUID.fromString(map.get("id")), map.get("name"), map.get("role"));
            userService.handle(userCreatedEvent);
        }
    }
}
