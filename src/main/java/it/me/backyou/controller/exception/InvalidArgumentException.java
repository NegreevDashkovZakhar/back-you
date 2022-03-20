package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidArgumentException extends ResponseStatusException {
    public InvalidArgumentException() {
        super(HttpStatus.BAD_REQUEST, "Invalid argument given");
    }
}
