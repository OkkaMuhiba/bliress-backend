package com.blibli.future.phase2.command.admin.employee.impl;

import com.blibli.future.phase2.command.admin.employee.DeleteEmployeeCommand;
import com.blibli.future.phase2.model.response.admin.employee.DeleteEmployeeResponse;
import com.blibli.future.phase2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteEmployeeCommandImpl implements DeleteEmployeeCommand {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<DeleteEmployeeResponse> execute(String request) {
        return userRepository.deleteById(request)
                .thenReturn(DeleteEmployeeResponse.builder()
                        .status(HttpStatus.ACCEPTED)
                        .message("Employee has been deleted")
                        .build())
                .onErrorReturn(DeleteEmployeeResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Employee cannot be deleted")
                        .build());
    }
}
