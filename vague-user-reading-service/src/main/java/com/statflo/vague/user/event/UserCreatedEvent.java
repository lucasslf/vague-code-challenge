/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.event;

import java.util.UUID;

/**
 *
 * @author lucas
 */
public class UserCreatedEvent {

    private UUID id;
    private String name;
    private String role;

    public UserCreatedEvent(UUID id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

}
