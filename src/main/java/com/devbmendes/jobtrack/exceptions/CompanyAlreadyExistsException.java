package com.devbmendes.jobtrack.exceptions;

public class CompanyAlreadyExistsException extends RuntimeException{
    public CompanyAlreadyExistsException(String message){
        super(message);
    }
}
