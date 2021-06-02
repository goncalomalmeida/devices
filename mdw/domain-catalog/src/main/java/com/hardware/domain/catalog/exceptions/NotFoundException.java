package com.hardware.domain.catalog.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    String description;

    public NotFoundException(String description) {
        super(description);
        this.description = description;
    }
}
