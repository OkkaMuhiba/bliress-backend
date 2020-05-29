package com.blibli.future.phase2.entity;

import com.blibli.future.phase2.entity.enumerate.Phase;
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
public class Training {
    @Id
    private String id;

    private String title;

    private String description;

    private Phase phase;

    private Set<Theory> theories;
}
