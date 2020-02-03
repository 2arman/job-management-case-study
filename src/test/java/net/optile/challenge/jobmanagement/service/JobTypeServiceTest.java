package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arman
 * Date: 2/3/20
 * Time: 11:19 AM
 **/
@SpringBootTest
class JobTypeServiceTest {

    @Autowired
    private JobTypeService jobTypeService;

    @Test
    void getAllJobTypes() {
        List<JobType> jobTypes = jobTypeService.getAllJobTypes();
        assertEquals(jobTypes.size() , 3);
        assertTrue(jobTypes.stream().anyMatch(jobType -> jobType.getName().equals("echo")));
        assertTrue(jobTypes.stream().anyMatch(jobType -> jobType.getName().equals("subtract")));
        assertTrue(jobTypes.stream().anyMatch(jobType -> jobType.getName().equals("multiply")));
    }
}