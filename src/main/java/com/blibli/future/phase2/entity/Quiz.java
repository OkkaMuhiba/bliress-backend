package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Quiz {
    @Id
    private String id;

    private String trainingId;

    private String theoryId;

    private Set<Question> questions;
}
