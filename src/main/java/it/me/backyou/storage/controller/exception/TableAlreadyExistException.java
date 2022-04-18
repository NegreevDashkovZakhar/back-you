package it.me.backyou.storage.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when query can not be processed, because specified table already exist
 */
public class TableAlreadyExistException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public TableAlreadyExistException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Table with such name already exists");
    }
}
