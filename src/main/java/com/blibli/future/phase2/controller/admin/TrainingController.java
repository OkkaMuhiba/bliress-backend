package com.blibli.future.phase2.controller.admin;

import com.blibli.future.phase2.command.admin.training.*;
import com.blibli.future.phase2.controller.ApiPath;
import com.blibli.future.phase2.entity.Training;
import com.blibli.future.phase2.model.command.admin.training.CreateTrainingRequest;
import com.blibli.future.phase2.model.command.admin.training.DeleteTrainingRequest;
import com.blibli.future.phase2.model.command.admin.training.GetByIdTrainingRequest;
import com.blibli.future.phase2.model.command.admin.training.UpdateTrainingRequest;
import com.blibli.future.phase2.model.response.admin.training.CreateTrainingResponse;
import com.blibli.future.phase2.model.response.admin.training.DeleteTrainingResponse;
import com.blibli.future.phase2.model.response.admin.training.GetAllTrainingResponse;
import com.blibli.future.phase2.model.response.admin.training.UpdateTrainingResponse;
import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Api
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainingController {
    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(ApiPath.ADMIN_TRAINING_CREATE)
    public Mono<Response<CreateTrainingResponse>> createTraining(@RequestBody CreateTrainingRequest request){
        return commandExecutor.execute(CreateTrainingCommand.class, request)
                .map(response -> ResponseHelper.status(response.getStatus(), response))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.ADMIN_TRAINING_GET_ALL)
    public Mono<Response<GetAllTrainingResponse>> getAllTraining(@RequestParam String batchId){
        return commandExecutor.execute(GetAllTrainingCommand.class, batchId)
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.ADMIN_TRAINING_GET_BY_ID)
    public Mono<Response<Training>> getByIdTraining(@RequestBody GetByIdTrainingRequest request){
        return commandExecutor.execute(GetByIdTrainingCommand.class, request)
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }

    @PutMapping(ApiPath.ADMIN_TRAINING_UPDATE)
    public Mono<Response<UpdateTrainingResponse>> updateTraining(@RequestBody UpdateTrainingRequest request){
        return commandExecutor.execute(UpdateTrainingCommand.class, request)
                .map(response -> ResponseHelper.status(response.getStatus(), response))
                .subscribeOn(Schedulers.elastic());
    }

    @DeleteMapping(ApiPath.ADMIN_TRAINING_DELETE)
    public Mono<Response<DeleteTrainingResponse>> deleteTraining(@RequestBody DeleteTrainingRequest request){
        return commandExecutor.execute(DeleteTrainingCommand.class, request)
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }
}
