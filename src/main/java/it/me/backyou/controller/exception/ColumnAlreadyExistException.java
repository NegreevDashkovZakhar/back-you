package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ColumnAlreadyExistException extends ResponseStatusException {
    public ColumnAlreadyExistException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Column already exists");
    }
}
