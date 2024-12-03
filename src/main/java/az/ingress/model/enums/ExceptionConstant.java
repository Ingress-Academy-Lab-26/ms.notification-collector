package az.ingress.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred"),
    QUEUE_EXCEPTION("QUEUE_EXCEPTION", "Failed to process message in the queue"),
    HTTP_METHOD_NOT_CORRECT("HTTP_METHOD_NOT_CORRECT", "method of the HTTP does not enter correctly");
    private final String code;
    private final String message;
}
