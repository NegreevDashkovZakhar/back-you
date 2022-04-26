package it.me.backyou.apikey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when user gives invalid or unregistered api key
 */
public class InvalidApiKeyException extends ResponseStatusException {
    /**
     * Default exception constructor initializing status code and error message
     */
    public InvalidApiKeyException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Invalid api key given");
    }
}
