package az.ingress.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.model.enums.ExceptionConstant.HTTP_METHOD_NOT_CORRECT;
import static az.ingress.model.enums.ExceptionConstant.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        log.error("Exception ", exception);
        return new ErrorResponse(UNEXPECTED_EXCEPTION.getCode(), UNEXPECTED_EXCEPTION.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException, ", ex);
        return new ErrorResponse(HTTP_METHOD_NOT_CORRECT.getCode(), HTTP_METHOD_NOT_CORRECT.getMessage());
    }

    @ExceptionHandler(QueueException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(QueueException ex) {
        log.error("QueueException, ", ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }
}
