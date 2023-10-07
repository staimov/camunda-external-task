package com.staimov.camunda_external_task.repository;

import com.staimov.camunda_external_task.entity.Innovation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnovationRepo extends MongoRepository<Innovation, String> {
}
