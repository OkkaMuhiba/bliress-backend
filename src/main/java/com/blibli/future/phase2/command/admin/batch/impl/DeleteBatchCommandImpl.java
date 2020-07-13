package com.blibli.future.phase2.command.admin.batch.impl;

import com.blibli.future.phase2.command.admin.batch.DeleteBatchCommand;
import com.blibli.future.phase2.model.response.admin.batch.DeleteBatchResponse;
import com.blibli.future.phase2.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteBatchCommandImpl implements DeleteBatchCommand {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Mono<DeleteBatchResponse> execute(String request) {
        return Mono.from(deleteBatch(request));
    }

    private Mono<DeleteBatchResponse> deleteBatch(String batchName){
        return batchRepository.deleteByBatchName(batchName)
                .thenReturn(DeleteBatchResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Batch has been deleted")
                        .build());
    }
}
