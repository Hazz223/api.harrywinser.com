package com.harry.winser.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by harry on 30/03/2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource Not found for given request")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
