package com.example.demo.exception;
import java.util.List;

public class GenericException extends ApiException{

    public GenericException(String... messages) { super(messages);}

    public GenericException(List<String> messages) { super(messages);}
}
