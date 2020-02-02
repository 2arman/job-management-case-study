package net.optile.challenge.jobmanagement.service.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@ToString
@Getter
public class JobTypeNotFoundException extends RuntimeException {
    private final String jobType;
    private final String message;

    public JobTypeNotFoundException(String jobType) {
        this.jobType = jobType;
        this.message = "the job type '" + jobType + "' did not find.";
    }

    public JobTypeNotFoundException(String message, String jobType) {
        super(message);
        this.message = message;
        this.jobType = jobType;
    }
}
