package net.optile.challenge.jobmanagement.service.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@ToString
@Getter
public class BadRequestException extends RuntimeException {
    private final String jobType;
    private final String message;

    public BadRequestException(String jobType) {
        this.jobType = jobType;
        this.message = "the job type '" + jobType + "' executed with invalid parameter(s).";
    }
}
