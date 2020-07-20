package com.blibli.future.phase2.command.admin.material.impl;

import com.blibli.future.phase2.command.admin.material.GetAllMaterialCommand;
import com.blibli.future.phase2.model.command.admin.material.GetAllMaterialRequest;
import com.blibli.future.phase2.model.response.admin.material.GetAllMaterialResponse;
import com.blibli.future.phase2.repository.TrainingMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashSet;

@Service
public class GetAllMaterialCommandImpl implements GetAllMaterialCommand {
    @Autowired
    private TrainingMaterialRepository trainingMaterialRepository;

    @Override
    public Mono<GetAllMaterialResponse> execute(GetAllMaterialRequest request) {
        return Mono.fromCallable(() -> getAllMaterial(request));
    }

    private GetAllMaterialResponse getAllMaterial(GetAllMaterialRequest request){

        return GetAllMaterialResponse.builder()
                .materialList(
                        new HashSet<>(Collections.unmodifiableList(
                                trainingMaterialRepository.findAllByBatchIdAndStage(
                                        request.getBatchId(), Integer.parseInt(request.getTraining())
                                ).collectList().block()
                        ))
                )
                .build();
    }
}
