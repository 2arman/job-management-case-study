package net.optile.challenge.jobmanagement.web.rest;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobManagementService;
import net.optile.challenge.jobmanagement.service.dto.JobDto;
import net.optile.challenge.jobmanagement.service.dto.JobReportResponse;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobRequest;
import net.optile.challenge.jobmanagement.service.dto.RegisterJobResponse;
import net.optile.challenge.jobmanagement.service.exceptions.JobNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 8:59 PM
 **/
@RestController("/api/v1/job")
@Api("Job Management Report")
@AllArgsConstructor
public class JobReportController {

    private final JobManagementService jobManagementService;

    @GetMapping(value = "/report" , params = {"page", "size"})
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
        return ResponseEntity.ok(jobManagementService.getAll(page, size));
    }
}
