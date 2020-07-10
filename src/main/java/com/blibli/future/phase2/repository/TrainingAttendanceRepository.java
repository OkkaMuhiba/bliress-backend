package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.TrainingAttendance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrainingAttendanceRepository extends ReactiveMongoRepository<String, TrainingAttendance> {

}
