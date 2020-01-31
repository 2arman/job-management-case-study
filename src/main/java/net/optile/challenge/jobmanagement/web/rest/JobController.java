package net.optile.challenge.jobmanagement.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.service.exceptions.JobNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:59 PM
 **/
@RestController("/api/v1/job/")
@Api("Job Management Controller")
@AllArgsConstructor
public class JobController {

    private final JobManagementService jobManagementService;

    @PostMapping
    @ApiOperation("register a job")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job detail.", response = JobDto.class),
            @ApiResponse(code = 400, message = "Bad request. invalid parameter format or not provide mandatory parameter"),
            @ApiResponse(code = 404, message = "The Job Type not found."),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<RegisterJobResponse> registerJob(@Valid @RequestBody RegisterJobRequest registerJobRequest) {
        return ResponseEntity.ok(jobManagementService.registerJob(registerJobRequest));
    }

    @GetMapping("/{jobId}")
    @ApiOperation(value = "Get a Job details", notes = "Returns a Job details and Job state.", response = JobDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job detail.", response = JobDto.class),
            @ApiResponse(code = 400, message = "Bad request. invalid parameter format or not provide mandatory parameter"),
            @ApiResponse(code = 404, message = "The Job not found."),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<JobDto> getJob(@PathVariable("jobId") @NotBlank @Pattern(regexp = "\\d+") String jobId)
            throws JobNotFoundException {
        return ResponseEntity.ok(jobManagementService.getJob(Long.valueOf(jobId)));
    }
}
