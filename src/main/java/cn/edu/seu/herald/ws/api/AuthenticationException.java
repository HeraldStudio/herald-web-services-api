package cn.edu.seu.herald.ws.api;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Not authenticated.");
    }
}
