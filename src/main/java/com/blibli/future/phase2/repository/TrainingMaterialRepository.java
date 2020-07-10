package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.TrainingMaterial;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrainingMaterialRepository extends ReactiveMongoRepository<TrainingMaterial, String> {

}
