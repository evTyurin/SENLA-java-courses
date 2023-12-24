package com.senlainc.warsaw.tyurin.exception;

public class ExpectationFailedException extends Exception {

    private String parameter;

    public ExpectationFailedException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}