/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.exception;

/**
 *
 * @author lucas
 */
public class UserDataRequiredException extends RuntimeException {

    public UserDataRequiredException() {
        //Change to some better message...
        super("User Data is Required");
    }
    
}
