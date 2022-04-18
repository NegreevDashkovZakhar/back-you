package it.me.backyou.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class thrown when users email is already in use
 */
public class UserAlreadyExistException extends ResponseStatusException {
    /**
     * Default exception constructor
     */
    public UserAlreadyExistException() {
        super(HttpStatus.NOT_ACCEPTABLE, "User with such email already exist");
    }
}
