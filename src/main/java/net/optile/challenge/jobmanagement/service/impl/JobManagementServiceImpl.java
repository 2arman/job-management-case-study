package net.optile.challenge.jobmanagement.service.impl;

import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:21 AM
 **/
public class JobManagementServiceImpl implements JobManagementService {
    @Override
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) {
        return RegisterJobResponse.builder().build();
    }
}
