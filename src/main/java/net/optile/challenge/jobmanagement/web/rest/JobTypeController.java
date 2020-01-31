package net.optile.challenge.jobmanagement.web.rest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 12:54 AM
 **/
@RestController("/api/v1/job/type")
@AllArgsConstructor
public class JobTypeController {

    private final JobTypeService jobTypeService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job types.", response = JobDto.class),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<List<JobType>> getJobTypes() {
        return ResponseEntity.ok(jobTypeService.getAllJobTypes());
    }
}
