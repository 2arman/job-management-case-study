package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 9:07 PM
 **/
@ApiModel
@Data
@Builder
public final class JobDto {
    @ApiModelProperty("job Id")
    private final String jobId;

    @ApiModelProperty(value = "job status", position = 1)
    private final JobStatus jobStatus;

    @ApiModelProperty(value = "job status", position = 2)
    private final String jobType;

    @ApiModelProperty(value = "job status", position = 3)
    private final Integer priority;

    @ApiModelProperty(value = "job execution method", position = 4)
    private final JobExecutionTypeDto jobExecutionType;

    @ApiModelProperty(value = "job parameter", position = 5)
    private final Map<String,String> parameters;

    @ApiModelProperty(value = "job created date", position = 6)
    private final LocalDateTime createdDate;
}
