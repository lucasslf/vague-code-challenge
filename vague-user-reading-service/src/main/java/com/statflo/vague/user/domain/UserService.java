/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.domain;

import com.statflo.vague.user.event.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lucas
 */
@Component
public class UserService {
    
    @Autowired
    UserRepository repository;
    
    public void handle(UserCreatedEvent event){
        User user = new User(event.getId(),event.getName(),event.getRole());
        repository.insert(user);
    }
}
