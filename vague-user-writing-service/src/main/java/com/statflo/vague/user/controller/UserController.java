/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.controller;

import com.statflo.vague.user.aggregate.UserService;
import com.statflo.vague.user.command.CreateUserCommand;
import com.statflo.vague.user.event.UserCreatedEvent;
import com.statflo.vague.user.exception.UserDataRequiredException;
import com.statflo.vague.user.infrastructure.kafka.KafkaUserSender;
import com.statflo.vague.user.vo.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping("/v1")
public class UserController {

    
    @Autowired
    UserService userService;
    
    @Autowired
    KafkaUserSender sender;
    
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        if ( (user == null) || ((null == user.getName() || null == user.getRole()))) {
            throw new UserDataRequiredException();
        } else {
            UserCreatedEvent userCreatedEvent = userService.process(new CreateUserCommand(user));
            sender.send(userCreatedEvent);
        }
    }
}
