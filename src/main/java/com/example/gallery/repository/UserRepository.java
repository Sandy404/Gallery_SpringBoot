package com.example.gallery.repository;

import com.example.gallery.model.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveMongoRepository<User, Integer> {
    User findByUsername(String username);


}
