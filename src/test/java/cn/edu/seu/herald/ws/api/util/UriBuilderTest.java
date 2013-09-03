package cn.edu.seu.herald.ws.api.util;

import junit.framework.TestCase;

import java.net.URI;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public class UriBuilderTest extends TestCase {

    public void testUriBuilder() {
        UriBuilder builder = UriBuilder.fromUri("http://herald.seu.edu.cn")
                .path("/a/{name}/c")
                .queryParam("foo", "bar")
                .queryParam("key", "v alue")
                .queryParam("中", "文");
        URI result = builder.build("b");
        assertEquals("http://herald.seu.edu.cn/a/b/c" +
                "?foo=bar&key=v+alue&%E4%B8%AD=%E6%96%87",
                result.toString());
    }
}
