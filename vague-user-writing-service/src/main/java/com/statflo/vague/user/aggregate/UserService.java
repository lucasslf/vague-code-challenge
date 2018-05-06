/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.aggregate;

import com.statflo.vague.user.command.CreateUserCommand;
import com.statflo.vague.user.event.UserCreatedEvent;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class UserService {

    public UserCreatedEvent process(CreateUserCommand createUserCommand) {
        UUID id = UUID.randomUUID();
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(id, createUserCommand.getUser().getName(), createUserCommand.getUser().getRole());
        return userCreatedEvent;
    }

}
