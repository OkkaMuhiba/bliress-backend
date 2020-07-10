package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
}
