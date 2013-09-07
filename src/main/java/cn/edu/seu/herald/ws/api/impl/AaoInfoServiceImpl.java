/*
 * The MIT License
 *
 * Copyright 2013 Herald Studio, Southeast University.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.AaoInfoService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.wink.common.model.rss.RssFeed;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
class AaoInfoServiceImpl extends AbstractXmlService
        implements AaoInfoService {

    private static final String AAO_URL = "/campus/{name}";
    private String baseResourceUri;

    public AaoInfoServiceImpl(RequestGetMethodFactory requestGetMethodFactory,
                              HttpClient httpClient, String baseResourceUri) {
        super(requestGetMethodFactory, httpClient);
        this.baseResourceUri = baseResourceUri;
    }

    public RssFeed getAaoRssFeed(String name, int limit) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(AAO_URL).queryParam("limit", limit);
        URI uri = builder.build(name);
        return getJaxbObjectByResource(uri, RssFeed.class);
    }

    @Override
    public RssFeed getAaoRssFeedBefore(String name, String uuid, int limit) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri) .path(AAO_URL)
                .queryParam("limit", limit)
                .queryParam("before", uuid);
        URI uri = builder.build(name);
        return getJaxbObjectByResource(uri, RssFeed.class);
    }

    @Override
    public RssFeed getAaoRssFeedAfter(String name, String uuid, int limit) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri).path(AAO_URL)
                .queryParam("limit", limit)
                .queryParam("after", uuid);
        URI uri = builder.build(name);
        return getJaxbObjectByResource(uri, RssFeed.class);
    }
}
