package com.blibli.future.phase2.model.command.admin.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTrainingRequest {
    private String batchId;

    private String training;

    private String date;

    private String location;

    private String timeStart;

    private String timeFinish;

    private String trainerId;

    private String trainerName;
}
