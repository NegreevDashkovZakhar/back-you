package it.me.backyou.storage.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when query could not find specified table
 */
public class NoSuchTableException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public NoSuchTableException() {
        super(HttpStatus.NOT_FOUND, "No such table found");
    }
}
