package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.domain.ExecutionMethod;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.domain.JobStatus;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import net.optile.challenge.jobmanagement.service.dto.JobExecutionTypeDto;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.util.TransactionalProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Arman
 * Date: 2/3/20
 * Time: 11:22 AM
 **/
@SpringBootTest
public class JobServicesITest {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    TransactionalProvider transactionProvider;

    @Autowired
    JobRegistrationService jobRegistrationService;

    @Test
    void registerJob_AndExecuted_Successfully() throws InterruptedException {
        RegisterJobRequest registerJobRequest =
                RegisterJobRequest.builder()
                        .jobExecutionType(
                                JobExecutionTypeDto.builder()
                                        .executionMethod(JobExecutionTypeDto.ExecutionMethod.IMMEDIATE)
                                        .build())
                        .jobType("echo")
                        .parameters(Collections.singletonMap("param", "test"))
                        .priority(0)
                        .build();
        RegisterJobResponse registerJobResponse = jobRegistrationService.registerJob(registerJobRequest);

        Thread.sleep(3000);

        transactionProvider.doWithTransactionReadOnly(
                () -> {
                    Job job = jobRepository.getOne(Long.valueOf(registerJobResponse.getJobId()));
                    assertEquals(job.getJobStatus(), JobStatus.SUCCESS);
                    assertEquals(job.getJobType(), "echo");
                    assertTrue(job.getCreatedDate().isBefore(LocalDateTime.now()));
                    assertEquals(job.getPriority(), 0);
                    assertEquals(job.getExecutionMethod(), ExecutionMethod.IMMEDIATE);
                    assertTrue(job.getExecutionTime().isBefore(LocalDateTime.now()));
                    assertEquals(job.getResult(), "test");
                });

    }

}
