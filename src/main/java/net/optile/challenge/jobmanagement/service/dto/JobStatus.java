package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum JobStatus {
    QUEUED,
    RUNNING,
    SUCCESS,
    FAILED
}