package com.staimov.camunda_external_task.service;

import com.staimov.camunda_external_task.entity.Innovation;
import com.staimov.camunda_external_task.repository.InnovationRepo;
import org.springframework.stereotype.Service;

@Service
public class InnovationService {
    private final InnovationRepo repo;

    public InnovationService(InnovationRepo repo) {
        this.repo = repo;
    }

    public void saveOrUpdate(Innovation innovation) {
        repo.save(innovation);
    }
}
