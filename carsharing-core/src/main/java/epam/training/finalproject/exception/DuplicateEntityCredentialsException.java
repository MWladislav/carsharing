package epam.training.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntityCredentialsException extends RuntimeException {

    public DuplicateEntityCredentialsException(String message) {
        super(message);
    }
}
