package com.staimov.camunda_external_task.handler;

import com.staimov.camunda_external_task.entity.Innovation;
import com.staimov.camunda_external_task.entity.Status;
import com.staimov.camunda_external_task.service.InnovationService;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("innovationReview") // create a subscription for this topic name
public class InnovationReviewHandler implements ExternalTaskHandler {

    private final InnovationService service;

    public InnovationReviewHandler(InnovationService service) {
        this.service = service;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // get variables
        String innovationId = externalTask.getVariable("innovationId");
        int innovationEffect = externalTask.getVariable("innovationEffect");
        int innovationCost = externalTask.getVariable("innovationCost");

        int innovationResult = innovationEffect - innovationCost;

        VariableMap newVariables = Variables.createVariables();
        newVariables.put("innovationResult", innovationResult);

        Innovation innovation = new Innovation(
                innovationId,
                innovationEffect,
                innovationCost,
                innovationResult,
                Status.ON_REVIEW);

        service.saveOrUpdate(innovation);

        // complete the external task
        externalTaskService.complete(externalTask, newVariables);

        Logger.getLogger("innovationReview")
                .log(Level.INFO, "The result for innovation {0} with effect {1} and cost {2} is calculated: {3}",
                        new Object[]{innovationId, innovationEffect, innovationCost, innovationResult});
    }
}
