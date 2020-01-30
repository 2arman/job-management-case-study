package net.optile.challenge.jobmanagement.service.mapper;

import net.optile.challenge.jobmanagement.domain.ExecutionMethod;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.domain.JobParameter;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobExecutionTypeDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 1:56 PM
 **/
@Mapper
@Component
public abstract class JobMapper {

    public Job map(JobDto jobDto) {
        return Job.builder()
                .id(Long.valueOf(jobDto.getJobId()))
                .createdDate(jobDto.getCreatedDate())
                .jobType(jobDto.getJobType())
                .priority(jobDto.getPriority())
                .parameters(map(jobDto.getParameters()))
                .executionMethod(map(jobDto.getJobExecutionType().getExecutionMethod()))
                .executionTime(jobDto.getJobExecutionType().getExecutionTime())
                .build();
    }

    public Job map(RegisterJobRequest jobDto) {
        return Job.builder()
                .createdDate(LocalDateTime.now())
                .jobType(jobDto.getJobType())
                .priority(jobDto.getPriority())
                .parameters(map(jobDto.getParameters()))
                .executionMethod(map(jobDto.getJobExecutionType().getExecutionMethod()))
                .executionTime(jobDto.getJobExecutionType().getExecutionTime())
                .build();
    }

    public JobDto map(Job job) {
        return JobDto.builder()
                .jobId(String.valueOf(job.getId()))
                .createdDate(job.getCreatedDate())
                .jobType(job.getJobType())
                .priority(job.getPriority())
                .parameters(map(job.getParameters()))
                .jobExecutionType(JobExecutionTypeDto.builder()
                        .executionMethod(map(job.getExecutionMethod()))
                        .executionTime(job.getExecutionTime())
                        .build())

                .build();
    }

    protected abstract JobExecutionTypeDto.ExecutionMethod map(ExecutionMethod executionMethod);

    private Map<String, String> map(List<JobParameter> parameters) {
        return parameters.stream().collect(
                Collectors.toMap(JobParameter::getKey, JobParameter::getValue));
    }

    protected abstract ExecutionMethod map(JobExecutionTypeDto.ExecutionMethod executionMethod);

    private List<JobParameter> map(Map<String, String> parameters) {
        return parameters.entrySet()
                .stream()
                .map(p -> JobParameter.builder()
                        .key(p.getKey())
                        .value(p.getValue())
                        .build())
                .collect(Collectors.toList());
    }

    public JobReportResponse map(Page<Job> pagedJobs) {
        List<JobDto> jobs = pagedJobs.stream().map(this::map).collect(Collectors.toList());
        return JobReportResponse
                .builder()
                .jobs(jobs)
                .totalJob(pagedJobs.getTotalElements())
                .build();
    }
}
