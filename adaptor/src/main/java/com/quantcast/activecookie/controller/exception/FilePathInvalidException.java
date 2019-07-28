package com.quantcast.activecookie.controller.exception;

public class FilePathInvalidException extends RuntimeException{

    public FilePathInvalidException(String message){
        super(message);
    }
}
