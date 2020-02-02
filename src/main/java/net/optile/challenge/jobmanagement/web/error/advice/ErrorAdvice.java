package net.optile.challenge.jobmanagement.web.error.advice;

import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.service.exception.BadRequestException;
import net.optile.challenge.jobmanagement.service.exception.JobNotFoundException;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author arman
 */

@RestControllerAdvice
@Slf4j
public final class ErrorAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseBody
    public RestError handleExceptions(JobNotFoundException ex, WebRequest req) {
        return getNotFoundRestError(ex.getClass().getSimpleName(), ex.getMessage(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JobTypeNotFoundException.class)
    @ResponseBody
    public RestError handleExceptions(JobTypeNotFoundException ex, WebRequest req) {
        return getNotFoundRestError(ex.getClass().getSimpleName(), ex.getMessage(), ex);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public RestError handleExceptions(BadRequestException ex, WebRequest req) {
        log.info("BadRequestException Exception occurred .");
        return RestError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionName(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestError handleExceptions(HttpMessageNotReadableException ex, WebRequest req) {
        log.info("HttpMessageNotReadableException Exception occurred .");
        return RestError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionName(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestError handleExceptions(Exception ex, WebRequest req) {
        log.warn("Service unhandled Exception occurred!: " + getStackTraceString(ex));
        return RestError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .exceptionName(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                //TODO The stack trace  is only for test environment and not for operational environment
                .stackTrace(getStackTraceString(ex).replaceAll("\t", "").split("\r\n"))
                .build();
    }

    private String getStackTraceString(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString().substring(0, 200);
    }

    private RestError getNotFoundRestError(String simpleName, String message, RuntimeException ex) {
        log.info(ex.getMessage());
        return RestError.builder()
                .status(HttpStatus.NOT_FOUND)
                .exceptionName(simpleName)
                .message(message)
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

}
