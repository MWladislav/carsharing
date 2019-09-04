package epam.training.finalproject.exceptions;

public class DuplicateEntityCredentialsException extends RuntimeException {

    public DuplicateEntityCredentialsException(String message) {
        super(message);
    }

    public DuplicateEntityCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntityCredentialsException(Throwable cause) {
        super(cause);
    }
}
