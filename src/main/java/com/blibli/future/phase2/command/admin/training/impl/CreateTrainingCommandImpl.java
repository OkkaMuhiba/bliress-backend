package com.blibli.future.phase2.command.admin.training.impl;

import com.blibli.future.phase2.command.admin.training.CreateTrainingCommand;
import com.blibli.future.phase2.entity.Training;
import com.blibli.future.phase2.model.command.admin.training.CreateTrainingRequest;
import com.blibli.future.phase2.model.response.admin.training.CreateTrainingResponse;
import com.blibli.future.phase2.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class CreateTrainingCommandImpl implements CreateTrainingCommand {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Mono<CreateTrainingResponse> execute(CreateTrainingRequest request) {
        return Mono.fromCallable(() -> createTraining(request))
                .flatMap(training -> trainingRepository.save(training))
                .map(training -> createResponse(HttpStatus.ACCEPTED, "Batch has been created"))
                .onErrorReturn(createResponse(HttpStatus.BAD_REQUEST, "Batch cannot be created"));
    }

    private Training createTraining(CreateTrainingRequest request){
        return Training.builder()
                .trainingId(UUID.randomUUID().toString())
                .batchId(request.getBatchId())
                .date(Date.from(Instant.parse(request.getDate())))
                .startedAt(Timestamp.from(Instant.parse(request.getTimeStart())))
                .endedAt(Timestamp.from(Instant.parse(request.getTimeFinish())))
                .location(request.getLocation())
                .stage(Integer.parseInt(request.getTraining()))
                .trainer(request.getTrainerId())
                .build();
    }

    private CreateTrainingResponse createResponse(HttpStatus status, String message){
        return CreateTrainingResponse.builder()
                .status(status)
                .message(message)
                .build();
    }
}
