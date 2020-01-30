package net.optile.challenge.jobmanagement.web.rest;

import lombok.AllArgsConstructor;
import net.optile.challenge.jobmanagement.service.JobTypeService;
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

    @GetMapping()
    public ResponseEntity<List<JobType>> getJob() {
        return ResponseEntity.ok(jobTypeService.getAll());
    }
}
