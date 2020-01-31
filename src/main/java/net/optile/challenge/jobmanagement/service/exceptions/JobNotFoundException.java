package net.optile.challenge.jobmanagement.service.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@ToString
@Getter
public class JobNotFoundException extends RuntimeException {
    private final Long jobId;
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    private final String message;

    public JobNotFoundException(Long jobId) {
        this.jobId = jobId;
        this.message = "the job with id " + jobId + "not found";
    }

    public JobNotFoundException(String message, Long jobId) {
        super(message);
        this.message = message;
        this.jobId = jobId;
    }
}
