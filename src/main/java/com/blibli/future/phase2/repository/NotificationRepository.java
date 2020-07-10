package com.blibli.future.phase2.repository;

import com.blibli.future.phase2.entity.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {

}
