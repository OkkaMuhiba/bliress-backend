package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.Batch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BatchRepository extends ReactiveMongoRepository<Batch, String> {

}
