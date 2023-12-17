package com.senlainc.warsaw.tyurin.exception;

public class CriteriaNotFoundException extends Exception {

    private String criteria;
    private int exceptionCode;

    public CriteriaNotFoundException(String criteria, int exceptionCode) {
        this.criteria = criteria;
        this.exceptionCode = exceptionCode;
    }

    public String getCriteria() {
        return criteria;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
