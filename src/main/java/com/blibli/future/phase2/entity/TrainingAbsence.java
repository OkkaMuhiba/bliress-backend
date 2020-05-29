package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.BSONTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TrainingAbsence {
    @Id
    private String id;

    private TrainingSchedule trainingSchedule;

    private User user;

    private BSONTimestamp absenceAt;
}
