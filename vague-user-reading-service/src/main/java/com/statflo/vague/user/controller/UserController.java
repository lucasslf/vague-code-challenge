/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.controller;

import com.statflo.vague.user.domain.User;
import com.statflo.vague.user.domain.UserRepository;
import java.util.List;
import static org.apache.tomcat.jni.Lock.name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserRepository repo;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(required = false) String name, @RequestParam(required = false) String role) {
        User user = new User(null, name, role);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("role", ignoreCase());

        return repo.findAll(Example.of(user,matcher));
}
}
