package net.optile.challenge.jobmanagement.service.impl;

import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.JobStatusManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobStatus;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 6:49 AM
 **/
@Service
@AllArgsConstructor
public class JobStatusManagementServiceImpl implements JobStatusManagementService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void UpdateStatus(Long jobId, JobStatus jobStatus, String result) {
        Job job = jobRepository.getOne(jobId);
        //todo check job state change can be apply?
        job.setJobStatus(jobMapper.map(jobStatus));
        job.setResult(result);
        jobRepository.save(job);
    }
}
