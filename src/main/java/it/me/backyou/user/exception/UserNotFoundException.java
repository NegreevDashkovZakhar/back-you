package it.me.backyou.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Excpetion thrown when specified user could not be found
 */
public class UserNotFoundException extends ResponseStatusException {
    /**
     * Default exception constructor initializing status code and message
     */
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }
}
