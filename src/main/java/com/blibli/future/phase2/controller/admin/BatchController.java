package com.blibli.future.phase2.controller.admin;

import com.blibli.future.phase2.command.admin.batch.CreateBatchCommand;
import com.blibli.future.phase2.command.admin.batch.DeleteBatchCommand;
import com.blibli.future.phase2.command.admin.batch.GetAllBatchCommand;
import com.blibli.future.phase2.controller.ApiPath;
import com.blibli.future.phase2.model.command.BlankRequest;
import com.blibli.future.phase2.model.command.admin.batch.CreateBatchRequest;
import com.blibli.future.phase2.model.response.admin.batch.CreateBatchResponse;
import com.blibli.future.phase2.model.response.admin.batch.DeleteBatchResponse;
import com.blibli.future.phase2.model.response.admin.batch.GetAllBatchResponse;
import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Api
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BatchController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(ApiPath.ADMIN_BATCH_CREATE)
    public Mono<Response<CreateBatchResponse>> createBatch(@RequestBody CreateBatchRequest request){
        return commandExecutor.execute(CreateBatchCommand.class, request)
                .map(response -> ResponseHelper.status(response.getStatus(), response))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.ADMIN_BATCH_GET_ALL)
    public Mono<Response<GetAllBatchResponse>> getAllBatch(){
        return commandExecutor.execute(GetAllBatchCommand.class, BlankRequest.builder().build())
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }

    @DeleteMapping(ApiPath.ADMIN_BATCH_DELETE)
    public Mono<Response<DeleteBatchResponse>> deleteBatchByBatchName(@RequestParam String batchName){
        return commandExecutor.execute(DeleteBatchCommand.class, batchName)
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }
}
