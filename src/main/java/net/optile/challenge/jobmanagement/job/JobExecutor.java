package net.optile.challenge.jobmanagement.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.service.JobReportService;
import net.optile.challenge.jobmanagement.service.JobStatusManagementService;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobStatus;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 11:37 PM
 **/
@Component
@AllArgsConstructor
@Slf4j
public class JobExecutor {

    private final JobTypeService jobTypeService;
    private final JobReportService jobReportService;
    private final JobStatusManagementService jobStatusManagementService;

    private String execute(String jobType, Map<String, String> parameters) throws JobTypeNotFoundException {
        JobDefinition selectedJob = jobTypeService.getAllJobDefinitions().stream()
                .filter(jobDefinition -> jobType.equals(jobDefinition.getJobType()))
                .findFirst()
                .orElseThrow(() -> new JobTypeNotFoundException(jobType));
        return selectedJob.run(parameters);
    }

    @Transactional
    public void execute(Long jobId){
        jobStatusManagementService.UpdateStatus(jobId , JobStatus.RUNNING,null);
        try {
            JobDto job = jobReportService.getJob(jobId);
            String executionResult = execute(job.getJobType(), job.getParameters());
            log.info("job {} finished successfully!", jobId);
            jobStatusManagementService.UpdateStatus(jobId , JobStatus.SUCCESS,executionResult);
        } catch (Throwable t) {
            log.info("job {} failed! ", jobId, t);
            jobStatusManagementService.UpdateStatus(jobId , JobStatus.FAILED,null);
        }
    }
}
