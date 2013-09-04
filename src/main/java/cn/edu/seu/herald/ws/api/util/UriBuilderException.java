package cn.edu.seu.herald.ws.api.util;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public class UriBuilderException extends RuntimeException {

    public UriBuilderException(String raw) {
        super(String.format("Unable to instantiate " +
                "the java.net.URI with java.lang.String [%s]", raw));
    }

    public UriBuilderException(Exception cause) {
        super(cause);
    }
}
