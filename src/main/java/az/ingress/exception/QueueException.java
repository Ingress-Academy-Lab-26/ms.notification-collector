package az.ingress.exception;

import lombok.Getter;

@Getter
public class QueueException extends RuntimeException {
    private final String code;

    public QueueException(String message, String code) {
        super(message);
        this.code = code;
    }
}
