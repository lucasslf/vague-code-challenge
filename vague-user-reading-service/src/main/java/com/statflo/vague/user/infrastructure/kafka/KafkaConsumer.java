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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.LoggerFactory;
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

    @KafkaListener(topics = "users", id = "users")
    public void listen(@Payload String event) throws Exception {
        Map<String, String> map = new HashMap();
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON string to Map
        map = mapper.readValue(event, new TypeReference<Map<String, String>>() {  });
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(UUID.fromString(map.get("id")), map.get("name"), map.get("role"));
        userService.handle(userCreatedEvent);
        LoggerFactory.getLogger(KafkaConsumer.class).debug(event);
    }
}
