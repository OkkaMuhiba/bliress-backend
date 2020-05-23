package com.blibli.future.phase2.command.auth;

import com.blibli.future.phase2.command.auth.impl.LoginCommandImpl;
import com.blibli.future.phase2.component.CustomPasswordEncoder;
import com.blibli.future.phase2.component.JwtTokenProvider;
import com.blibli.future.phase2.entity.User;
import com.blibli.future.phase2.model.command.LoginRequest;
import com.blibli.future.phase2.model.response.LoginResponse;
import com.blibli.future.phase2.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginCommandImplTest {
    @InjectMocks
    LoginCommandImpl loginCommand;

    @Mock
    UserRepository userRepository;

    @Mock
    CustomPasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    private User user;
    private LoginResponse expectedSuccess;

    @BeforeEach
    void setUp(){
        initMocks(this);
        user = User.builder()
                .username("testing1")
                .password("password")
                .build();

        expectedSuccess = LoginResponse.builder()
                .token("token")
                .message("SUCCESS")
                .build();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void executeSuccess_test(){
        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(Mono.just(user));

        given(jwtTokenProvider.generateToken(user))
                .willReturn("token");

        LoginRequest request = LoginRequest.builder()
                .username("testing1")
                .password("password")
                .build();

        given(passwordEncoder.encode(request.getPassword()))
                .willReturn(request.getPassword());

        LoginResponse result = loginCommand.execute(request).block();

        assertEquals(expectedSuccess, result);
        verify(userRepository).findByUsername(request.getUsername());
    }

    @Test
    void executeWrongUsername_test(){
        given(jwtTokenProvider.generateToken(user))
                .willReturn("token");

        LoginRequest request = LoginRequest.builder()
                .username("testing11")
                .password("password")
                .build();

        given(userRepository.findByUsername(request.getUsername()))
                .willReturn(Mono.error(new NotFoundException("No user account was found with email: " + request.getUsername())));

        given(passwordEncoder.encode(request.getPassword()))
                .willReturn(request.getPassword());

        LoginResponse result = loginCommand.execute(request).block();

        assertNotEquals(expectedSuccess, result);
        assertEquals(null, result.getToken());
        assertEquals("Username or Password is wrong", result.getMessage());
        verify(userRepository).findByUsername(request.getUsername());
    }

    @Test
    void executeWrongPassword_test(){
        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(Mono.just(user));

        given(jwtTokenProvider.generateToken(user))
                .willReturn("token");

        LoginRequest request = LoginRequest.builder()
                .username("testing1")
                .password("passwordd")
                .build();

        given(passwordEncoder.encode(request.getPassword()))
                .willReturn(request.getPassword());

        LoginResponse result = loginCommand.execute(request).block();

        assertNotEquals(expectedSuccess, result);
        assertEquals(null, result.getToken());
        assertEquals("Username or Password is wrong", result.getMessage());
        verify(userRepository).findByUsername(request.getUsername());
    }
}
