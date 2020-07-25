package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.Training;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TrainingRepository extends ReactiveMongoRepository<Training, String> {
    Mono<Training> findByBatchIdAndStage(String batchId, Integer stage);

    Flux<Training> findAllByTrainerId(String trainerId);

    Flux<Training> findAllByBatchId(String batchId);
}
