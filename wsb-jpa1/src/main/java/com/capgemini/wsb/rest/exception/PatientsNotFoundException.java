package com.capgemini.wsb.rest.exception;

public class PatientsNotFoundException extends RuntimeException {

    public PatientsNotFoundException()
    {
        super("No patients found");
    }
}