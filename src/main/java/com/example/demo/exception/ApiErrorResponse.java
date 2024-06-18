package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private List<String> messages = new ArrayList<>();

    public static ApiErrorResponse build(HttpStatus httpStatus, ApiException ex, WebRequest webRequest) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(httpStatus.value());
        apiErrorResponse.setError(httpStatus.getReasonPhrase());
        apiErrorResponse.setMessages(ex.getMessages());
        return apiErrorResponse;
    }
}
