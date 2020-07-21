package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Test {
    @Id
    private String testId;

    private String batchId;

    private Integer stage;

    private String materialId;

    private Date available;

    private Date closed;

    private Integer timeLimit;

    private Set<Question> questions;
}
