package com.blibli.future.phase2.controller.admin;

import com.blibli.future.phase2.command.admin.batch.CreateBatchCommand;
import com.blibli.future.phase2.controller.ApiPath;
import com.blibli.future.phase2.model.command.admin.batch.CreateBatchRequest;
import com.blibli.future.phase2.model.response.admin.batch.CreateBatchResponse;
import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }
}
