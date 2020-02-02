package net.optile.challenge.jobmanagement.service.mapper;

import net.optile.challenge.jobmanagement.domain.ExecutionMethod;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.domain.JobParameter;
import net.optile.challenge.jobmanagement.domain.JobStatus;
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

    public Job map(RegisterJobRequest jobDto) {
        Job job = Job.builder()
                .createdDate(LocalDateTime.now())
                .jobType(jobDto.getJobType())
                .priority(jobDto.getPriority())
                .jobStatus(JobStatus.QUEUED)
                .executionMethod(map(jobDto.getJobExecutionType().getExecutionMethod()))
                .executionTime(mapExecutionTime(jobDto.getJobExecutionType().getExecutionTime(), jobDto.getJobExecutionType().getExecutionMethod()))
                .build();
        job.setParameters(map(jobDto.getParameters(), job));
        return job;
    }

    private LocalDateTime mapExecutionTime(LocalDateTime executionTime, JobExecutionTypeDto.ExecutionMethod executionMethod) {
        return executionMethod == JobExecutionTypeDto.ExecutionMethod.SCHEDULED ? executionTime : LocalDateTime.now();
    }

    public JobDto map(Job job) {
        return JobDto.builder()
                .jobId(String.valueOf(job.getId()))
                .createdDate(job.getCreatedDate())
                .result(job.getResult())
                .jobType(job.getJobType())
                .jobStatus(map(job.getJobStatus()))
                .priority(job.getPriority())
                .parameters(map(job.getParameters()))
                .jobExecutionType(JobExecutionTypeDto.builder()
                        .executionMethod(map(job.getExecutionMethod()))
                        .executionTime(job.getExecutionTime())
                        .build())

                .build();
    }

    protected abstract net.optile.challenge.jobmanagement.service.dto.JobStatus map(JobStatus jobStatus);

    public abstract JobStatus map(net.optile.challenge.jobmanagement.service.dto.JobStatus jobStatus);

    protected abstract JobExecutionTypeDto.ExecutionMethod map(ExecutionMethod executionMethod);

    public Map<String, String> map(List<JobParameter> parameters) {
        return parameters.stream().collect(
                Collectors.toMap(JobParameter::getKey, JobParameter::getValue));
    }

    protected abstract ExecutionMethod map(JobExecutionTypeDto.ExecutionMethod executionMethod);

    private List<JobParameter> map(Map<String, String> parameters, Job job) {
        return parameters.entrySet()
                .stream()
                .map(p -> JobParameter.builder()
                        .job(job)
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
