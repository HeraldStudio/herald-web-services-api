package cn.edu.seu.herald.ws.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public class UriBuilder {

    private StringBuilder uriBuilder;
    private StringBuilder queryParamsBuilder;

    public static UriBuilder fromUri(String uri) {
        return new UriBuilder(uri);
    }

    public UriBuilder(String uri) {
        uriBuilder = new StringBuilder(uri);
        queryParamsBuilder = new StringBuilder();
    }

    public UriBuilder path(String path) {
        uriBuilder.append(path);
        return this;
    }

    public UriBuilder queryParam(String key, String value)
            throws UriBuilderException {
        queryParamsBuilder.append(
                (queryParamsBuilder.length() == 0)
                        ? "?" : "&");
        try {
            String htmlEncodedKey = URLEncoder.encode(key, "UTF-8");
            String htmlEncodedValue = URLEncoder.encode(value, "UTF-8");
            queryParamsBuilder.append(String.format("%s=%s",
                    htmlEncodedKey, htmlEncodedValue));
            return this;
        } catch (UnsupportedEncodingException ex) {
            throw new UriBuilderException(ex);
        }
    }

    public URI build(Object ...objs) throws UriBuilderException {
        String raw = uriBuilder.toString();
        for (Object obj : objs) {
            raw = raw.replaceFirst("\\{[\\w]+}",
                    (obj == null) ? "" : obj.toString());
        }
        String uri = raw + queryParamsBuilder.toString();
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new UriBuilderException(raw);
        }
    }
}
