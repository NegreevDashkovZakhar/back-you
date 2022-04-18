package it.me.backyou.storage.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when argument in request can not be identified
 */
public class UnknownArgumentException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public UnknownArgumentException() {
        super(HttpStatus.BAD_REQUEST, "Unknown argument passed");
    }
}
