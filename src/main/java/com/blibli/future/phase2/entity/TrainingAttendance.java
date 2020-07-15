package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TrainingAttendance {
    @Id
    private String id;

    private String batchName;

    private Integer stage;

    private String userId;

    private Boolean status;
}
