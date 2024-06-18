package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
abstract class ApiException extends RuntimeException{
    private final List<String> messages;

    protected ApiException() {
        super();
        this.messages = new ArrayList<>();
    }

    protected ApiException(List<String> messages) {
        super();
        this.messages = new ArrayList<>();
    }

    protected ApiException(String... messages) {
        super();
        this.messages = Arrays.asList(messages);
    }
}
