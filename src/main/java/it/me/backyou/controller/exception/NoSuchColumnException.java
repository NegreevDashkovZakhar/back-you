package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchColumnException extends ResponseStatusException {
    public NoSuchColumnException() {
        super(HttpStatus.NOT_FOUND, "No such column found");
    }
}
