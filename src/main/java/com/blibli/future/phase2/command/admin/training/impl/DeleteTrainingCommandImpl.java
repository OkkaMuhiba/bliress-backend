package com.blibli.future.phase2.command.admin.training.impl;

import com.blibli.future.phase2.command.admin.training.DeleteTrainingCommand;
import com.blibli.future.phase2.model.command.admin.training.DeleteTrainingRequest;
import com.blibli.future.phase2.model.response.admin.training.DeleteTrainingResponse;
import com.blibli.future.phase2.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteTrainingCommandImpl implements DeleteTrainingCommand {
    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public Mono<DeleteTrainingResponse> execute(DeleteTrainingRequest request) {
        return Mono.from(deleteTraining(request));
    }

    private Mono<DeleteTrainingResponse> deleteTraining(DeleteTrainingRequest request){
        return trainingRepository.deleteByBatchNameAndStage(
                request.getBatchId(),
                Integer.parseInt(request.getTraining())
        ).thenReturn(DeleteTrainingResponse.builder()
                .status(HttpStatus.OK)
                .message("Training has been deleted")
                .build());

    }
}
