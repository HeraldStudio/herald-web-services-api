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

import org.apache.commons.httpclient.methods.GetMethod;

import java.net.URI;

class RequestGetMethodFactoryImpl implements RequestGetMethodFactory {

    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String MEDIA_TYPE_APPLICATION_XML = "application/xml";
    private static final String MEDIA_TYPE_TEXT_CSV = "text/csv";
    private static final String MEDIA_TYPE_TEXT_PLAIN = "text/plain";

    @Override
    public  GetMethod newXmlRequestGetMethod(URI uri) {
        GetMethod getMethod = new GetMethod(uri.toASCIIString());
        getMethod.setRequestHeader(HTTP_HEADER_ACCEPT,
                MEDIA_TYPE_APPLICATION_XML);
        return getMethod;
    }

    @Override
    public GetMethod newCsvRequestGetMethod(URI uri) {
        GetMethod getMethod = new GetMethod(uri.toASCIIString());
        getMethod.setRequestHeader(HTTP_HEADER_ACCEPT,
                MEDIA_TYPE_TEXT_CSV);
        return getMethod;
    }

    @Override
    public GetMethod newPlainTextRequestMethod(URI uri) {
        GetMethod getMethod = new GetMethod(uri.toASCIIString());
        getMethod.setRequestHeader(HTTP_HEADER_ACCEPT,
                MEDIA_TYPE_TEXT_PLAIN);
        return getMethod;
    }
}
