package com.quantcast.activecookie.controller;

public class NoCookieFoundException extends RuntimeException {

    public NoCookieFoundException(String message){
        super(message);
    }
}
