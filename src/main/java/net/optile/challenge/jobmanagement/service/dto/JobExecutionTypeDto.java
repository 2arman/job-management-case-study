package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel
@Data
@Builder
public final class JobExecutionTypeDto {
    @ApiModelProperty(value = "execution method", required = true)
    @NotNull
    private final ExecutionMethod executionMethod;

    @ApiModelProperty(value = "execution method", position = 1, notes = "format : [yyyy-MM-dd HH:mm:ss]")
    private final LocalDateTime executionTime;

    @ApiModel
    public enum ExecutionMethod {
        IMMEDIATE,
        SCHEDULED
    }
}