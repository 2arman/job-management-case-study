package net.optile.challenge.jobmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.JobReportService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.exception.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 6:53 AM
 **/
@Service
@AllArgsConstructor
@Slf4j
public class JobReportServiceImpl implements JobReportService {
    public static final String CREATED_DATE = "createdDate";
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

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
