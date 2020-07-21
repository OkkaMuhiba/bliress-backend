package com.blibli.future.phase2.controller.admin;

import com.blibli.future.phase2.command.admin.test.CreateTestCommand;
import com.blibli.future.phase2.command.admin.test.GetByIdTestCommand;
import com.blibli.future.phase2.controller.ApiPath;
import com.blibli.future.phase2.entity.Test;
import com.blibli.future.phase2.model.command.admin.test.CreateTestRequest;
import com.blibli.future.phase2.model.command.admin.test.GetByIdTestRequest;
import com.blibli.future.phase2.model.response.admin.test.CreateTestResponse;
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
public class TestController {
    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(ApiPath.ADMIN_TEST_CREATE)
    public Mono<Response<CreateTestResponse>> createTest(@RequestBody CreateTestRequest request){
        return commandExecutor.execute(CreateTestCommand.class, request)
                .map(response -> ResponseHelper.status(response.getStatus(), response))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(ApiPath.ADMIN_TEST_GET_QUESTION)
    public Mono<Response<Test>> getByIdTest(@RequestBody GetByIdTestRequest request){
        return commandExecutor.execute(GetByIdTestCommand.class, request)
                .map(response -> ResponseHelper.ok(response))
                .subscribeOn(Schedulers.elastic());
    }
}
