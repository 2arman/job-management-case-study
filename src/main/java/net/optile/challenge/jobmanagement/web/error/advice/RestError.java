package net.optile.challenge.jobmanagement.web.error.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author arman
 */
@Data
@Builder
public class RestError implements Serializable {

    private final String exceptionName;
    private final HttpStatus status;
    private final int code;
    private final String subSystemCode;
    private final String message;
    private final String[] stackTrace;
}
