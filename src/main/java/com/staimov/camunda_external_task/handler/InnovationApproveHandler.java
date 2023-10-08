package com.staimov.camunda_external_task.handler;

import com.staimov.camunda_external_task.entity.Innovation;
import com.staimov.camunda_external_task.entity.Status;
import com.staimov.camunda_external_task.service.InnovationService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("innovationApprove") // create a subscription for this topic name
public class InnovationApproveHandler implements ExternalTaskHandler {

    private final InnovationService service;

    public InnovationApproveHandler(InnovationService service) {
        this.service = service;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
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
                    Status.APPROVED);

            service.saveOrUpdate(innovation);

            // complete the external task
            externalTaskService.complete(externalTask);

            Logger.getLogger("innovationApprove")
                    .log(Level.INFO, "The innovation {0} with result {1} is APPROVED!",
                            new Object[]{innovationId, innovationResult});
        } catch (Exception e) {
            externalTaskService.handleFailure(externalTask, e.getMessage(), ExceptionUtils.getStackTrace(e), 0, 0L);
        }
    }
}
