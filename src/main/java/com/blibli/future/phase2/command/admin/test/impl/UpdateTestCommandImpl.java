package com.blibli.future.phase2.command.admin.test.impl;

import com.blibli.future.phase2.command.admin.test.UpdateTestCommand;
import com.blibli.future.phase2.entity.Test;
import com.blibli.future.phase2.model.command.admin.test.UpdateTestRequest;
import com.blibli.future.phase2.model.response.admin.test.UpdateTestResponse;
import com.blibli.future.phase2.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.Instant;

public class UpdateTestCommandImpl implements UpdateTestCommand {
    @Autowired
    private TestRepository testRepository;

    @Override
    public Mono<UpdateTestResponse> execute(UpdateTestRequest request) {
        return getTest(request)
                .map(test -> updateTest(test, request))
                .flatMap(test -> testRepository.save(test))
                .map(test -> createResponse(HttpStatus.ACCEPTED, "Test has been updated"))
                .onErrorReturn(createResponse(HttpStatus.BAD_REQUEST, "Test cannot be updated"));
    }

    private Mono<Test> getTest(UpdateTestRequest request){
        return testRepository.findByBatchIdAndStageAndAndMaterialId(
                request.getBatchId(),
                Integer.parseInt(request.getTraining()),
                request.getMaterialId()
        );
    }

    private Test updateTest(Test test, UpdateTestRequest request){
        test.setAvailable(Date.from(Instant.parse(request.getAvailable())));
        test.setClosed(Date.from(Instant.parse(request.getClosed())));
        test.setTimeLimit(request.getTimeLimit());
        test.setQuestions(request.getQuestions());

        return test;
    }

    private UpdateTestResponse createResponse(HttpStatus status, String message){
        return UpdateTestResponse.builder()
                .status(status)
                .message(message)
                .build();
    }
}
