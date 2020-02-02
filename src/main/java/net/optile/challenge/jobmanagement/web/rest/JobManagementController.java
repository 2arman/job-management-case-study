package net.optile.challenge.jobmanagement.web.rest;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobReportService;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.*;
import net.optile.challenge.jobmanagement.service.exception.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.JobRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:59 PM
 **/
@RestController("/api/v1")
@Api("Job Management Controller")
@AllArgsConstructor
public class JobManagementController {

    private final JobRegistrationService jobRegistrationService;
    private final JobReportService jobReportService;
    private final JobTypeService jobTypeService;

    @PostMapping("/job")
    @ApiOperation("register a job")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job detail.", response = JobDto.class),
            @ApiResponse(code = 400, message = "Bad request. invalid parameter format or not provide mandatory parameter"),
            @ApiResponse(code = 404, message = "The Job Type not found."),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<RegisterJobResponse> registerJob(@Valid @RequestBody RegisterJobRequest registerJobRequest) {
        return ResponseEntity.ok(jobRegistrationService.registerJob(registerJobRequest));
    }

    @GetMapping("/job/{jobId}")
    @ApiOperation(value = "Get a Job details", notes = "Returns a Job details and Job state.", response = JobDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job detail.", response = JobDto.class),
            @ApiResponse(code = 400, message = "Bad request. invalid parameter format or not provide mandatory parameter"),
            @ApiResponse(code = 404, message = "The Job not found."),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<JobDto> getJob(@PathVariable("jobId") @NotBlank @Pattern(regexp = "\\d+") String jobId)
            throws JobNotFoundException {
        return ResponseEntity.ok(jobReportService.getJob(Long.valueOf(jobId)));
    }

    @GetMapping(value = "/jobs", params = {"page", "size"})
    @ApiOperation(value = "Get all Jobs", notes = "Returns all Jobs details and Their state.", response = JobDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Jobs detail.", response = JobReportResponse.class),
            @ApiResponse(code = 400, message = "Bad request. invalid parameter format or not provide mandatory parameter"),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<JobReportResponse> getAllJob(@Valid @ApiParam("number of page - zero based")
                                                       @RequestParam("page") int page,
                                                       @ApiParam(value = "number of items in pages",
                                                               defaultValue = "15")
                                                       @Valid @RequestParam(value = "size",
                                                               required = false,
                                                               defaultValue = "15") Integer size) {
        return ResponseEntity.ok(jobReportService.getAll(page, size));
    }

    @GetMapping("/types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of Job types.", response = JobDto.class),
            @ApiResponse(code = 500, message = "Internal server error."),
    })
    public ResponseEntity<List<JobType>> getJobTypes() {
        return ResponseEntity.ok(jobTypeService.getAllJobTypes());
    }
}
