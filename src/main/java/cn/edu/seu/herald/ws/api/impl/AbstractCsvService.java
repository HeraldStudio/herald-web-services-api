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

import cn.edu.seu.herald.ws.api.ConfigurableService;
import cn.edu.seu.herald.ws.api.ServiceException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;

import java.net.URI;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
abstract class AbstractCsvService implements ConfigurableService {

    private static final int DEFAULT_TIMEOUT = 5000;
    private final RequestGetMethodFactory requestGetMethodFactory;
    private final HttpClient httpClient;

    AbstractCsvService(RequestGetMethodFactory requestGetMethodFactory,
                       HttpClient httpClient) {
        this.requestGetMethodFactory = requestGetMethodFactory;
        this.httpClient = httpClient;
        setConnectionTimeout(DEFAULT_TIMEOUT);
    }

    @Override
    public void setConnectionTimeout(int timeout) {
        httpClient.getParams().setParameter(
                HttpConnectionParams.CONNECTION_TIMEOUT, timeout);
    }

    protected String[] getCsvByResouse(URI uri) {
        GetMethod getMethod = requestGetMethodFactory
                .newCsvRequestGetMethod(uri);
        try {
            String csv = getMethod.getResponseBodyAsString();
            return csv.split(",");
        } catch (Exception ex) {
            throw new ServiceException(ex);
        } finally {
            getMethod.releaseConnection();
        }
    }
}
