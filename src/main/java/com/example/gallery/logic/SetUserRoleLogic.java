package com.example.gallery.logic;

import com.example.gallery.model.user.Role;
import com.example.gallery.model.user.User;
import com.example.gallery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
public class SetUserRoleLogic {
    private final UserRepository userRepository;

    public Mono<User> setUserRoleAndSave(Tuple2<Long, User> tuple) {
        int index = tuple.getT1().intValue();
        User user = tuple.getT2();
        if (index == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }
}
