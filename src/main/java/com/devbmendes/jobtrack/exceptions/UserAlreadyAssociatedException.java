package com.devbmendes.jobtrack.exceptions;



public class UserAlreadyAssociatedException extends RuntimeException {

    public UserAlreadyAssociatedException(String message) {
        super(message);
    }
}