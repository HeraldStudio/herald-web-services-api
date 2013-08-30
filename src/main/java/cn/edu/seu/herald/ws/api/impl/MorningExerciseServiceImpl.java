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
import cn.edu.seu.herald.ws.api.MorningExerciseService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.exercise.RunTimesData;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class MorningExerciseServiceImpl extends AbstractXmlService
        implements MorningExerciseService {

    private static final String EXERCISE_PATH = "/exercise";
    private static final String REMAIN_DAYS_PATH = EXERCISE_PATH + "/remain";
    private static final String RUNTIME_PATH = EXERCISE_PATH + "/runtime";
    private final RequestGetMethodFactory requestGetMethodFactory;
    private final HttpClient httpClient;
    private final String baseResourceUri;

    public MorningExerciseServiceImpl(
            RequestGetMethodFactory requestGetMethodFactory,
            HttpClient httpClient, String baseResourceUri) {
        super(requestGetMethodFactory, httpClient);
        this.requestGetMethodFactory = requestGetMethodFactory;
        this.httpClient = httpClient;
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public int getRemainDays() throws ServiceException {
        try {
            UriBuilder builder = UriBuilder.fromPath(
                    baseResourceUri + REMAIN_DAYS_PATH);
            URI uri = builder.build();
            GetMethod getMethod = requestGetMethodFactory
                    .newPlainTextRequestMethod(uri);
            String daysStr = null;
            try {
                httpClient.executeMethod(getMethod);
                int status = getMethod.getStatusCode();
                if (status != 200) {
                    throw new UnexpectedStatusException(status);
                }
                daysStr = getMethod.getResponseBodyAsString();
                return Integer.parseInt(daysStr);
            } catch (NumberFormatException ex) {
                throw new ServiceException(String.format(
                        "NumberFormatException occurred " +
                                "during parsing [ %s ] with uri [ %s ]",
                        daysStr, getMethod.getURI().toString()), ex);
            } finally {
                getMethod.releaseConnection();
            }
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RunTimesData getRunTimesData(String username, String password)
            throws AuthenticationException, ServiceException {
        UriBuilder builder = UriBuilder.fromPath(baseResourceUri + RUNTIME_PATH)
                .queryParam("username", username)
                .queryParam("password", password);
        URI uri = builder.build();
        return getJaxbObjectByResourceWithAuthentication(uri,
                RunTimesData.class);
    }
}
