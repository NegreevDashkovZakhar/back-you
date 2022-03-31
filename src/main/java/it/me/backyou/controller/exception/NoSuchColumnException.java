package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when query could not find specified column
 */
public class NoSuchColumnException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public NoSuchColumnException() {
        super(HttpStatus.NOT_FOUND, "No such column found");
    }
}
