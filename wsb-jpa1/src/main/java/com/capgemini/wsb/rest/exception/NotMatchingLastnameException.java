package com.capgemini.wsb.rest.exception;

public class NotMatchingLastnameException extends RuntimeException {

    public NotMatchingLastnameException(String lastName)
    {
        super("There is no patient with lastname " + lastName);
    }
}