package com.blibli.future.phase2.entity;

import com.blibli.future.phase2.entity.Training;
import com.blibli.future.phase2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.BSONTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TrainingSchedule {
    @Id
    private String id;

    private BSONTimestamp startedAt;

    private BSONTimestamp endedAt;

    private Training training;

    private Set<User> trainers;

    private String batch;
}
