package it.me.backyou.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

public class UnknownException extends ResponseStatusException {
    public UnknownException() {
        super(HttpStatus.valueOf(500), "Unknown error");
    }
}
