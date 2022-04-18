package it.me.backyou.storage.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when application receives invalid argument and can not process request
 */
public class InvalidArgumentException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public InvalidArgumentException() {
        super(HttpStatus.BAD_REQUEST, "Invalid argument given");
    }
}
