package com.blibli.future.phase2.model.command.admin.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeRequest {
    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String division;

    private String birthdate;

    private String gender;

    private String batchId;
}
