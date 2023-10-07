package com.staimov.camunda_external_task.handler;

import com.staimov.camunda_external_task.entity.Innovation;
import com.staimov.camunda_external_task.entity.Status;
import com.staimov.camunda_external_task.service.InnovationService;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("innovationReject") // create a subscription for this topic name
public class InnovationRejectHandler implements ExternalTaskHandler {

    private final InnovationService service;

    public InnovationRejectHandler(InnovationService service) {
        this.service = service;
    }

    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // get variables
        String innovationId = externalTask.getVariable("innovationId");
        int innovationEffect = externalTask.getVariable("innovationEffect");
        int innovationCost = externalTask.getVariable("innovationCost");
        int innovationResult = externalTask.getVariable("innovationResult");

        Innovation innovation = new Innovation(
                innovationId,
                innovationEffect,
                innovationCost,
                innovationResult,
                Status.REJECTED);

        service.saveOrUpdate(innovation);

        // complete the external task
        externalTaskService.complete(externalTask);

        Logger.getLogger("innovationReject")
                .log(Level.INFO, "The innovation {0} with result {1} is REJECTED!",
                        new Object[]{innovationId, innovationResult});
    }
}
