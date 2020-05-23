package com.blibli.future.phase2.command.auth;

import com.blibli.future.phase2.model.command.CreateUserRequest;
import com.blibli.future.phase2.model.response.CreateUserResponse;
import com.blibli.oss.command.Command;
import reactor.core.publisher.Mono;

public interface CreateUserCommand extends Command<CreateUserRequest, CreateUserResponse> {

}
