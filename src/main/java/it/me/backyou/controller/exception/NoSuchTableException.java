package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchTableException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public NoSuchTableException() {
        super(HttpStatus.NOT_FOUND, "No such table found");
    }
}
