package net.optile.challenge.jobmanagement.service.impl;

import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.event.publisher.GuavaEventPublisher;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.service.exceptions.BadRequestException;
import net.optile.challenge.jobmanagement.service.exceptions.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.exceptions.JobTypeNotFoundException;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:21 AM
 **/
@Service
@AllArgsConstructor
public class JobManagementServiceImpl implements JobManagementService {
    public static final String CREATED_DATE = "createdDate";
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobTypeService jobTypeService;
    private final GuavaEventPublisher guavaEventPublisher;

    @Override
    @Transactional
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) throws JobTypeNotFoundException, BadRequestException {
        boolean validParam = jobTypeService.getAllJobDefinitions()
                .stream()
                .filter(jobDefinition -> jobDefinition.getJobType().equals(registerJobRequest.getJobType()))
                .findFirst()
                .orElseThrow(() -> new JobTypeNotFoundException(registerJobRequest.getJobType()))
                .validate(registerJobRequest.getParameters());
        if (!validParam) {
            throw new BadRequestException(registerJobRequest.getJobType());
        }
        Job job = jobRepository.save(jobMapper.map(registerJobRequest));
        guavaEventPublisher.publishEvent(job);
        return RegisterJobResponse.builder().jobId(String.valueOf(job.getId())).build();
    }

    @Override
    @Transactional(readOnly = true)
    public JobDto getJob(Long jobId) throws JobNotFoundException {
        return jobMapper.map(jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId)));
    }

    @Override
    @Transactional(readOnly = true)
    public JobReportResponse getAll(int page, Integer size) {
        Pageable pagable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(CREATED_DATE)));
        Page<Job> jobs = jobRepository.findAll(pagable);
        return jobMapper.map(jobs);
    }
}
