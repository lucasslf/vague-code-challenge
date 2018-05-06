/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statflo.vague.user.domain;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author lucas
 */
public interface UserRepository extends MongoRepository<User, UUID>{
}
