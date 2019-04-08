package com.kakaopay.housing.auth.conf;


public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException(String msg) {
        super(msg);
    }
}
