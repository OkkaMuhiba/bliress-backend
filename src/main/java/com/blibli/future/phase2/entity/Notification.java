package com.blibli.future.phase2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Notification {
    @Id
    private String id;

    private String title;

    private String message;

    private String userId;

    private Boolean onRead;
}
