package net.optile.challenge.jobmanagement.service.impl;

import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.service.exceptions.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    @Override
    public RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) {
        Job job = jobRepository.save(jobMapper.map(registerJobRequest));
        return RegisterJobResponse.builder().jobId(String.valueOf(job.getId())).build();
    }

    @Override
    public JobReportResponse getAll(int page, Integer size) {
        Pageable pagable =  PageRequest.of(page,size, Sort.by(Sort.Order.desc(CREATED_DATE)));
        Page<Job> jobs = jobRepository.findAll(pagable);
        return jobMapper.map(jobs);
    }

    @Override
    public JobDto getJob(Long jobId) throws JobNotFoundException {
        return jobMapper.map(jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId)));
    }
}
