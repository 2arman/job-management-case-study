package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobExecutionTypeDto;
import net.optile.challenge.jobmanagement.service.dto.JobType;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.exception.BadRequestException;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;
import org.junit.jupiter.api.Test;
import org.mapstruct.util.Experimental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arman
 * Date: 2/3/20
 * Time: 11:09 AM
 **/
@SpringBootTest
class JobRegistrationServiceTest {

    @Autowired
    private JobRegistrationService jobRegistrationService;


    @Test
    void registerJob_NotFoundJobType() {
        assertThrows(JobTypeNotFoundException.class,
                () -> {
                    RegisterJobRequest registerJobRequest =
                            RegisterJobRequest.builder()
                                    .jobExecutionType(
                                            JobExecutionTypeDto.builder()
                                                    .executionMethod(JobExecutionTypeDto.ExecutionMethod.IMMEDIATE)
                                                    .build())
                                    .jobType("unknown")
                                    .priority(0)
                                    .build();
                    jobRegistrationService.registerJob(registerJobRequest);
                });
    }

    @Test
    void registerJob_BadRequestTypeParameter() {
        assertThrows(BadRequestException.class,
                () -> {
                    RegisterJobRequest registerJobRequest =
                            RegisterJobRequest.builder()
                                    .jobExecutionType(
                                            JobExecutionTypeDto.builder()
                                                    .executionMethod(JobExecutionTypeDto.ExecutionMethod.IMMEDIATE)
                                                    .build())
                                    .jobType("echo")
                                    .parameters(Collections.singletonMap("fault", "test"))
                                    .priority(0)
                                    .build();
                    jobRegistrationService.registerJob(registerJobRequest);
                });
    }

    @Test
    void registerJob_Success() {
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
        assertEquals("1",
                jobRegistrationService.registerJob(registerJobRequest).getJobId());
    }
}