package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TableAlreadyExistException extends ResponseStatusException {
    public TableAlreadyExistException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Table with such name already exists");
    }
}
