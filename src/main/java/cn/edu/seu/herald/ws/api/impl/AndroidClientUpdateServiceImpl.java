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

import cn.edu.seu.herald.ws.api.AndroidClientUpdateService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.update.Update;
import org.apache.commons.httpclient.HttpClient;

import javax.ws.rs.core.UriBuilder;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class AndroidClientUpdateServiceImpl extends AbstractXmlService
        implements AndroidClientUpdateService {

    private static final String UPDATE_PATH = "/update";
    private String baseResourceUri;

    public AndroidClientUpdateServiceImpl(
            RequestGetMethodFactory requestGetMethodFactory,
            HttpClient httpClient,
            String baseResourceUri) {
        super(requestGetMethodFactory, httpClient);
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public Update getNewVersion() throws ServiceException {
        UriBuilder builder = UriBuilder.fromPath(baseResourceUri + UPDATE_PATH);
        return getJaxbObjectByResource(builder.build(), Update.class);
    }
}
