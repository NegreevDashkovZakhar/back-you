package it.me.backyou.storage.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception thrown when application tries to create a column, but the column with such name already exist
 */
public class ColumnAlreadyExistException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public ColumnAlreadyExistException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Column already exists");
    }
}
