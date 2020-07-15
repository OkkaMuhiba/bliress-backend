package com.blibli.future.phase2.command.admin.training.impl;

import com.blibli.future.phase2.command.admin.training.GetAllTrainingCommand;
import com.blibli.future.phase2.model.response.admin.training.GetAllTrainingResponse;
import com.blibli.future.phase2.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;

@Service
public class GetAllTrainingCommandImpl implements GetAllTrainingCommand {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Mono<GetAllTrainingResponse> execute(String request) {
        return Mono.fromCallable(() -> createResponse(request));
    }

    public GetAllTrainingResponse createResponse(String request){
        return GetAllTrainingResponse.builder()
                .trainingList(
                        new HashSet<>(Collections.unmodifiableList(
                                trainingRepository.findAll().collectList().block()
                        )
                ))
                .build();
    }
}
