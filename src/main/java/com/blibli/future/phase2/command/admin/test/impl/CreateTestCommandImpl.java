package com.blibli.future.phase2.command.admin.test.impl;

import com.blibli.future.phase2.command.admin.test.CreateTestCommand;
import com.blibli.future.phase2.entity.Question;
import com.blibli.future.phase2.entity.Test;
import com.blibli.future.phase2.model.command.admin.test.CreateTestRequest;
import com.blibli.future.phase2.model.response.admin.test.CreateTestResponse;
import com.blibli.future.phase2.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

@Service
public class CreateTestCommandImpl implements CreateTestCommand {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Mono<CreateTestResponse> execute(CreateTestRequest request) {
        return Mono.from(checkIfTestIsExist(request))
                .flatMap(result -> (result) ? Mono.just(null) : Mono.just(createTest(request)))
                .flatMap(test -> (test == null) ? Mono.just(null) : testRepository.save(test))
                .map(user -> createResponse(HttpStatus.ACCEPTED, "Test has been created"))
                .onErrorReturn(createResponse(HttpStatus.BAD_REQUEST, "Test cannot be created"));
    }

    private Mono<Boolean> checkIfTestIsExist(CreateTestRequest request){
        return Mono.from(testRepository.findByBatchIdAndStageAndAndMaterialId(
                request.getBatchId(),
                Integer.parseInt(request.getTraining()),
                request.getMaterialId()
        )
                .switchIfEmpty(Mono.just(Test.builder().build()))
                .map(test -> test.getTestId() != null));
    }

    private Test createTest(CreateTestRequest request){
        return Test.builder()
                .testId(UUID.randomUUID().toString())
                .batchId(request.getBatchId())
                .stage(Integer.parseInt(request.getTraining()))
                .materialId(request.getMaterialId())
                .available(request.getAvailable())
                .closed(request.getClosed())
                .timeLimit(request.getTimeLimit())
                .questions(request.getQuestions())
                .build();
    }

    private CreateTestResponse createResponse(HttpStatus status, String message){
        return CreateTestResponse.builder()
                .status(status)
                .message(message)
                .build();
    }

    private Instant convertStringDateToInstant(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}