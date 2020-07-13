package com.blibli.future.phase2.command.admin.batch.impl;

import com.blibli.future.phase2.command.admin.batch.GetByIdBatchCommand;
import com.blibli.future.phase2.model.response.admin.batch.GetByIdBatchResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetByIdBatchCommandImpl implements GetByIdBatchCommand {
    @Override
    public Mono<GetByIdBatchResponse> execute(String request) {
        return null;
    }
}
