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

import cn.edu.seu.herald.ws.api.AuthenticationException;
import cn.edu.seu.herald.ws.api.ConfigurableService;
import cn.edu.seu.herald.ws.api.ServiceException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
abstract class AbstractXmlService implements ConfigurableService {

    private static final int DEFAULT_TIMEOUT = 5000;
    private HttpClient httpClient;
    private XmlObjectUnmarshaller xmlObjectUnmarshaller =
            new Dom4jXmlObjectUnmarshaller();
    private final RequestGetMethodFactory requestGetMethodFactory;

    AbstractXmlService(RequestGetMethodFactory requestGetMethodFactory,
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

    /**
     *
     * @param <T> Jaxb类
     * @param uri 资源的URI
     * @param clz Jaxb类
     * @return 资源对应的Jaxb对象
     * @throws ServiceException 与服务交流异常
     */
    protected <T> T getJaxbObjectByResource(URI uri, Class<T> clz)
            throws ServiceException {
        GetMethod getMethod = requestGetMethodFactory
                .newXmlRequestGetMethod(uri);
        try {
            int status = httpClient.executeMethod(getMethod);
            InputStream responseStream = getMethod.getResponseBodyAsStream();
            try {
                return handleResponse(status, responseStream, clz);
            } finally {
                responseStream.close();
            }
        } catch (IOException ex) {
            throw new ServiceException(ex);
        } finally {
            getMethod.releaseConnection();
        }
    }

    /**
     *
     * @param <T> Jaxb类
     * @param uri 资源的URI
     * @param clz Jaxb类
     * @return 资源对应的Jaxb对象
     * @throws ServiceException 与服务交流异常
     */
    protected <T> T getJaxbObjectByResourceWithAuthentication(URI uri,
                                                              Class<T> clz)
            throws AuthenticationException, ServiceException {
        GetMethod getMethod = requestGetMethodFactory
                .newXmlRequestGetMethod(uri);
        try {
            int status = httpClient.executeMethod(getMethod);
            InputStream responseStream = getMethod.getResponseBodyAsStream();
            try {
                return handleResponseWithAuthentication(status, responseStream, clz);
            } finally {
                responseStream.close();
            }
        } catch (IOException ex) {
            throw new ServiceException(ex);
        } finally {
            getMethod.releaseConnection();
        }
    }

    private <T> T handleResponse(int status, InputStream responseStream,
                                 Class<T> clz)
            throws ServiceException {
        try {
            switch (status) {
                case 200:
                    return xmlObjectUnmarshaller.unmarshal(responseStream, clz);
                default:
                    throw new UnexpectedStatusException(status);
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    private <T> T handleResponseWithAuthentication(int status,
                                                   InputStream responseStream,
                                                   Class<T> clz)
            throws AuthenticationException, ServiceException {
        try {
            switch (status) {
                case 200:
                    return xmlObjectUnmarshaller.unmarshal(responseStream, clz);
                case 401:
                    throw new AuthenticationException();
                default:
                    throw new UnexpectedStatusException(status);
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
