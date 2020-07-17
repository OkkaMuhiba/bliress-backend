package com.blibli.future.phase2.command.admin.trainer.impl;

import com.blibli.future.phase2.command.admin.trainer.DeleteTrainerCommand;
import com.blibli.future.phase2.model.response.admin.trainer.DeleteTrainerResponse;
import com.blibli.future.phase2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteTrainerCommandImpl implements DeleteTrainerCommand {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<DeleteTrainerResponse> execute(String request) {
        return userRepository.deleteById(request)
                .thenReturn(DeleteTrainerResponse.builder()
                        .status(HttpStatus.ACCEPTED)
                        .message("Trainer has been deleted")
                        .build())
                .onErrorReturn(DeleteTrainerResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message("Trainer cannot be deleted")
                        .build());
    }
}
