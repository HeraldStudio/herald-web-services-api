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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
abstract class AbstractXmlService implements ConfigurableService {

    private static final int DEFAULT_TIMEOUT = 5000;
    private Client client;

    AbstractXmlService() {
        client = Client.create();
        client.setConnectTimeout(DEFAULT_TIMEOUT);
    }

    @Override
    public void setConnectionTimeout(int timeout) {
        client.setConnectTimeout(timeout);
    }

    protected WebResource getWebResource(URI uri) {
        return client.resource(uri);
    }

    /**
     *
     * @param <T> Jaxb类
     * @param uri 资源的URI
     * @param clz Jaxb类
     * @return 资源对应的Jaxb对象，如果资源没有变动（304，Not-Modified）则返回空
     * @throws ServiceException 与服务交流异常
     */
    protected <T> T getJaxbObjectByResource(URI uri, Class<T> clz)
            throws ServiceException {
        WebResource resource = client.resource(uri);
        ClientResponse response = resource
                .accept(MediaType.APPLICATION_XML_TYPE)
                .get(ClientResponse.class);
        return handleResponse(response, clz);
    }

    /**
     *
     * @param <T> Jaxb类
     * @param uri 资源的URI
     * @param clz Jaxb类
     * @return 资源对应的Jaxb对象，如果资源没有变动（304，Not-Modified）则返回空
     * @throws ServiceException 与服务交流异常
     */
    protected <T> T getJaxbObjectByResourceWithAuthentication(URI uri,
                                                              Class<T> clz)
            throws AuthenticationException, ServiceException {
        WebResource resource = client.resource(uri);
        ClientResponse response = resource
                .accept(MediaType.APPLICATION_XML_TYPE)
                .get(ClientResponse.class);
        return handleResponseWithAuthentication(response, clz);
    }

    /**
     *
     * @param <T> Jaxb类
     * @param uri 资源的URI
     * @param clientUUID 当前资源持有的资源的UUID
     * @param clz Jaxb类
     * @return 资源对应的Jaxb对象，如果资源没有变动（304，Not-Modified）则返回空
     * @throws ServiceException 与服务交流异常
     */
    protected <T> T getJaxbObjectByResource(URI uri, String clientUUID,
            Class<T> clz) throws ServiceException {
        WebResource resource = client.resource(uri);
        ClientResponse response = resource
                .accept(MediaType.APPLICATION_XML_TYPE)
                .header("If-None-Match", clientUUID)
                .get(ClientResponse.class);
        return handleResponse(response, clz);
    }

    private <T> T handleResponse(ClientResponse response, Class<T> clz)
            throws ServiceException {
        try {
            int status = response.getStatus();
            switch (status) {
                case 200:
                    return response.getEntity(clz);
                default:
                    throw new UnexpectedStatusException(status);
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    private <T> T handleResponseWithAuthentication(ClientResponse response,
                                                   Class<T> clz)
            throws AuthenticationException, ServiceException {
        try {
            int status = response.getStatus();
            switch (status) {
                case 200:
                    return response.getEntity(clz);
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
