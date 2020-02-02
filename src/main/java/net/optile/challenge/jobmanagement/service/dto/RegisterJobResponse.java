package net.optile.challenge.jobmanagement.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:05 AM
 **/
@ApiModel
@Data
@Builder
public final class RegisterJobResponse {

    @ApiModelProperty("job registration id")
    private final String jobId;
}
