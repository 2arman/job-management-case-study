package net.optile.challenge.jobmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.event.publisher.EventPublisher;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.JobRegistrationService;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.*;
import net.optile.challenge.jobmanagement.service.exception.BadRequestException;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:21 AM
 **/
@Service
@AllArgsConstructor
@Slf4j
public class JobRegistrationServiceImpl implements JobRegistrationService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobTypeService jobTypeService;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) throws JobTypeNotFoundException, BadRequestException {
        jobTypeService.validateTypeAndParameters(registerJobRequest.getJobType(), registerJobRequest.getParameters());
        Job job = jobRepository.save(jobMapper.map(registerJobRequest));
        log.info("Thread Name : {}", Thread.currentThread().getName());
        eventPublisher.publishEvent(job);
        return RegisterJobResponse.builder().jobId(String.valueOf(job.getId())).build();
    }
}
