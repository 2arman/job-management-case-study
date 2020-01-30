package net.optile.challenge.jobmanagement.service.impl;

import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:21 AM
 **/
@Service
public class JobManagementServiceImpl implements JobManagementService {
    @Override
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) {
        return RegisterJobResponse.builder().build();
    }

    @Override
    public JobReportResponse getAll(int page, Integer size) {
        return JobReportResponse.builder().build();
    }

    @Override
    public JobDto getJob(String jobId) {
        return null;
    }
}
