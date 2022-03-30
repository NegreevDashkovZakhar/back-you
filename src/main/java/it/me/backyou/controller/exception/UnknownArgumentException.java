package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownArgumentException extends ResponseStatusException {
    /**
     * Default constructor initializing ResponseStatusException
     */
    public UnknownArgumentException() {
        super(HttpStatus.BAD_REQUEST, "Unknown argument passed");
    }
}
