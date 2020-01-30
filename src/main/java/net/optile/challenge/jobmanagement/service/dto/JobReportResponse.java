package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:06 AM
 **/
@ApiModel
@Data
@Builder
public final class JobReportResponse {

    @ApiModelProperty("total Number of job")
    private final long totalJob;

    @ApiModelProperty("list of job ordered by creation date descending")
    private final List<JobDto> jobs;
}
