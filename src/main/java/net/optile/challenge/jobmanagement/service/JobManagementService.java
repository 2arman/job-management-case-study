package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.service.exceptions.BadRequestException;
import net.optile.challenge.jobmanagement.service.exceptions.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.exceptions.JobTypeNotFoundException;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 7:47 AM
 **/
public interface JobManagementService {

    RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) throws JobTypeNotFoundException, BadRequestException;

    JobReportResponse getAll(int page, Integer size);

    JobDto getJob(Long jobId) throws JobNotFoundException;
}
