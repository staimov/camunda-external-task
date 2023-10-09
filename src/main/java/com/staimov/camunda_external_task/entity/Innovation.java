package com.staimov.camunda_external_task.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Innovation {
    // Field without an annotation but named id maps to the identity field.
    // https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template.id-handling
    private String id;
    private Integer effect;
    private Integer cost;
    private Integer result;
    private Status status;

    public Innovation() {
    }

    public Innovation(String id, Integer effect, Integer cost, Integer result, Status status) {
        this.id = id;
        this.effect = effect;
        this.cost = cost;
        this.result = result;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Innovation{");
        sb.append("id='").append(id).append('\'');
        sb.append(", effect=").append(effect);
        sb.append(", cost=").append(cost);
        sb.append(", result=").append(result);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
