package com.example.gallery.service;


import com.example.gallery.model.authentication.AuthenticationRequest;
import com.example.gallery.model.authentication.AuthenticationResponse;
import com.example.gallery.model.user.User;
import com.example.gallery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPhone()
                )
        );

        var user = userRepository.findByUsername(authenticationRequest.getUsername());

        User userDetails = new User();
        userDetails.setUsername(user.getUsername());
        userDetails.setPhone(user.getPhone());
        userDetails.setRole(user.getRole());

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder().token(String.valueOf(jwtToken)).build();
    }
}
