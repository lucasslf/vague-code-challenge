/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.command;

import com.statflo.vague.user.vo.User;

/**
 *
 * @author lucas
 */
public class CreateUserCommand {
    private User user;

    public CreateUserCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    
}
