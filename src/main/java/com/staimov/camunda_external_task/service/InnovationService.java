package com.staimov.camunda_external_task.service;

import com.staimov.camunda_external_task.entity.Innovation;

public interface InnovationService {
    void saveOrUpdate(Innovation innovation);
}
