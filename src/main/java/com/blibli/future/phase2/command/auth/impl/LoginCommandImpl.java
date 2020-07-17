package com.blibli.future.phase2.command.auth.impl;

import com.blibli.future.phase2.command.auth.LoginCommand;
import com.blibli.future.phase2.component.CustomPasswordEncoder;
import com.blibli.future.phase2.component.JwtTokenProvider;
import com.blibli.future.phase2.entity.User;
import com.blibli.future.phase2.model.command.LoginRequest;
import com.blibli.future.phase2.model.response.LoginResponse;
import com.blibli.future.phase2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Slf4j
@Service
public class LoginCommandImpl implements LoginCommand {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<LoginResponse> execute(LoginRequest request) {
        return findUserByUsermail(request.getUsermail()).map((userDetails) -> {
            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
                return createResponse(jwtTokenProvider.generateToken(userDetails), "SUCCESS");
            } else {
                return createResponse(null, "Username or Password is wrong");
            }
        }).onErrorReturn(createResponse(null, "Username or Password is wrong"));
    }

    private Mono<User> findUserByUsermail(String usermail){
        return userRepository.findByUsermail(usermail)
                .switchIfEmpty(Mono.error(new Exception("No user account was found with email: " + usermail)));
    }

    private LoginResponse createResponse(String token, String message){
        return LoginResponse.builder().token(token).message(message).build();
    }


}
