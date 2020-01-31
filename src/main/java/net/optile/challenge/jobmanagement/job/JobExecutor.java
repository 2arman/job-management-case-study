package net.optile.challenge.jobmanagement.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobType;
import net.optile.challenge.jobmanagement.service.exceptions.JobTypeNotFoundException;
import net.optile.challenge.jobmanagement.service.mapper.JobMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 11:37 PM
 **/
@Component
@AllArgsConstructor
@Slf4j
public class JobExecutor {

    private final JobMapper jobMapper;
    private final JobTypeService jobTypeService;

    public String execute(Job job) throws JobTypeNotFoundException {
        JobDefinition selectedJob = jobTypeService.getAllJobDefinitions().stream()
                .filter(jobDefinition -> job.getJobType().equals(jobDefinition.getJobType()))
                .findFirst()
                .orElseThrow(() -> new JobTypeNotFoundException(job.getJobType()));
        return selectedJob.run(jobMapper.map(job.getParameters()));
    }
}
