package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:24 AM
 **/
@ApiModel
@Data
@Builder
public final class JobType {
    @ApiModelProperty(value = "job type name", position = 0)
    private final String name;

    @ApiModelProperty(value = "job type required parameters", position = 1)
    private final Set<String> parameters;
}
