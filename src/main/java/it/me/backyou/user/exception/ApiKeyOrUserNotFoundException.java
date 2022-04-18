package it.me.backyou.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when specified user or api key was not found
 */
public class ApiKeyOrUserNotFoundException extends ResponseStatusException {
    /**
     * Default constructor initializing status code and message
     */
    public ApiKeyOrUserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User or api key was not found");
    }
}
