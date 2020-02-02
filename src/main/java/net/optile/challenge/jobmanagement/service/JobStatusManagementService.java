package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobStatus;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 6:46 AM
 **/
public interface JobStatusManagementService {
    void UpdateStatus(Long jobId, JobStatus jobStatus, String result);
}
