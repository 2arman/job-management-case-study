package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:06 AM
 **/
@ApiModel
@Data
@Builder
public final class RegisterJobRequest {
    @ApiModelProperty(value = "job type", position = 0)
    @NotNull
    private final String jobType;

    @ApiModelProperty(value = "job priority", position = 1)
    private final Integer priority;

    @ApiModelProperty(value = "job execution method", position = 2)
    @NotNull
    private final JobExecutionTypeDto jobExecutionType;

    @ApiModelProperty(value = "job parameters", position = 3)
    private final Map<String,String> parameters;
}
