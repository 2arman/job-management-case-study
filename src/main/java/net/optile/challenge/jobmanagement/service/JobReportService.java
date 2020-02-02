package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.exception.JobNotFoundException;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 6:53 AM
 **/
public interface JobReportService {
    JobReportResponse getAll(int page, Integer size);

    JobDto getJob(Long jobId) throws JobNotFoundException;
}
