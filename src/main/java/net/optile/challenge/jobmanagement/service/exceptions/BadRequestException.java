package net.optile.challenge.jobmanagement.service.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@ToString
@Getter
public class BadRequestException extends RuntimeException {
    private final String jobType;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private final String message;


    public BadRequestException(String jobType) {
        this.jobType = jobType;
        this.message = "the job type : " + jobType + " execute with invalid parameter";
    }


}
