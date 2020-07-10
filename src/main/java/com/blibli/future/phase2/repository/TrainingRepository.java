package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.Training;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrainingRepository extends ReactiveMongoRepository<String, Training> {

}
