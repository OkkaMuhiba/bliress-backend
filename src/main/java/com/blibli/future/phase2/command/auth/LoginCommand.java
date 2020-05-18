package com.blibli.future.phase2.command.auth;

import com.blibli.future.phase2.component.CustomPasswordEncoder;
import com.blibli.future.phase2.component.JwtTokenProvider;
import com.blibli.future.phase2.entity.User;
import com.blibli.future.phase2.model.command.LoginRequest;
import com.blibli.future.phase2.model.response.LoginResponse;
import com.blibli.future.phase2.repository.UserRepository;
import com.blibli.oss.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginCommand implements Command<LoginRequest, LoginResponse> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<LoginResponse> execute(LoginRequest request) {
        return findUserByUsername(request.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
                return createResponse(jwtTokenProvider.generateToken(userDetails));
            } else {
                return createResponse("");
            }
        }).defaultIfEmpty(createResponse(""));
    }

    private Mono<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private LoginResponse createResponse(String token){
        return LoginResponse.builder().token(token).build();
    }


}
