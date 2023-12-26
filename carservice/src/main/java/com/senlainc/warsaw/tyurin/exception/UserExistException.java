package com.senlainc.warsaw.tyurin.exception;

public class UserExistException extends Exception {

    private String username;

    public UserExistException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
