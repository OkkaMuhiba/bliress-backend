package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Training {
    @Id
    private String id;

    private String batchName;

    private Integer stage;

    private Date date;

    private Timestamp startedAt;

    private Timestamp endedAt;

    private String location;

    private User trainer;
}
