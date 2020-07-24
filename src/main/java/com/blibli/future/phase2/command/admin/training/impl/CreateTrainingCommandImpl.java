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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class CreateTrainingCommandImpl implements CreateTrainingCommand {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Mono<CreateTrainingResponse> execute(CreateTrainingRequest request) {
        return Mono.from(checkIfTrainingIsExist(request))
                .flatMap(result -> (result) ? Mono.just(null) : Mono.just(createTraining(request)))
                .flatMap(training -> (training == null) ? Mono.just(null) : trainingRepository.save(training))
                .map(training -> createResponse(HttpStatus.ACCEPTED, "Training has been created"))
                .onErrorReturn(createResponse(HttpStatus.BAD_REQUEST, "Training cannot be created"));
    }

    private Training createTraining(CreateTrainingRequest request){
        return Training.builder()
                .trainingId(UUID.randomUUID().toString())
                .batchId(request.getBatchId())
                .date(request.getDate())
                .startedAt(request.getTimeStart())
                .endedAt(request.getTimeFinish())
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

    public Mono<Boolean> checkIfTrainingIsExist(CreateTrainingRequest request){
        return Mono.from(trainingRepository.findByBatchIdAndStage(request.getBatchId(), Integer.parseInt(request.getTraining()))
                .switchIfEmpty(Mono.just(Training.builder().build()))
                .map(user -> user.getTrainingId() != null));
    }
}
