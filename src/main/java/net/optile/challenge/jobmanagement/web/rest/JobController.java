package net.optile.challenge.jobmanagement.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<RegisterJobResponse> registerJob(@Valid @RequestBody RegisterJobRequest registerJobRequest) {
        return ResponseEntity.ok(jobManagementService.registerJob(registerJobRequest));
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<JobReportResponse> getAllJob(@Valid @ApiParam("number of page - zero based")
                                         @RequestParam("page") int page,
                                                       @ApiParam(value = "number of items in pages",
                                                 defaultValue = "15")
                                         @Valid @RequestParam(value = "size",
                                                 required = false,
                                                 defaultValue = "15") Integer size) {
        return ResponseEntity.ok(jobManagementService.getAll(page,size));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getJob(@Valid @PathVariable("jobId") String jobId) {
        return ResponseEntity.ok(jobManagementService.getJob(jobId));
    }
}
