package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 7:47 AM
 **/
public interface JobManagementService {

    RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest);
}
