package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsedNameException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public UsedNameException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Name is already in use");
    }
}
