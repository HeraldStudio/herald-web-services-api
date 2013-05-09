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

import cn.edu.seu.herald.ws.api.CampusInfoService;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.apache.wink.common.model.atom.AtomFeed;
import org.apache.wink.common.model.rss.RssFeed;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class CampusInfoServiceImpl extends AbstractXmlService
        implements CampusInfoService {

    private static final String AAO_URL = "/campus/aao";
    private String baseResourceUri;

    public CampusInfoServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    public AtomFeed getAaoAtomFeed() {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri).path(AAO_URL);
        URI uri = builder.build();
        return getJaxbObjectByResource(uri, AtomFeed.class);
    }

    public AtomFeed getAaoAtomFeed(String uuid) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri).path(AAO_URL);
        URI uri = builder.build();
        return getJaxbObjectByResource(uri, uuid, AtomFeed.class);
    }

    public RssFeed getAaoRssFeed() {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(AAO_URL);
        URI uri = builder.build();
        return getJaxbObjectByResource(uri, RssFeed.class);
    }

    public RssFeed getAaoRssFeed(String uuid) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(AAO_URL);
        URI uri = builder.build();
        return getJaxbObjectByResource(uri, uuid, RssFeed.class);
    }
}
