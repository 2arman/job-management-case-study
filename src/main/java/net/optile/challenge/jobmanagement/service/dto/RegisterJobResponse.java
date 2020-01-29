package net.optile.challenge.jobmanagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:05 AM
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterJobResponse {
   private long jobId;
}
