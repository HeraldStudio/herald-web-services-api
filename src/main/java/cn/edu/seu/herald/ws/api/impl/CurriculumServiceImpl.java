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

import cn.edu.seu.herald.ws.api.Curriculum;
import cn.edu.seu.herald.ws.api.CurriculumService;
import cn.edu.seu.herald.ws.api.ServiceException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.StringReader;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * 课程表服务的实现类，基于jersey实现的RESTful Web Services的客户端。
 * @author rAy <predator.ray@gmail.com>
 */
public class CurriculumServiceImpl implements CurriculumService {

    private WebResource resource;

    public CurriculumServiceImpl(String resourceUri) {
        Client c = Client.create();
        resource = c.resource(resourceUri);
    }

    public Curriculum getCurriculum(String cardNumber) throws ServiceException {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("cardNumber", cardNumber);
        return getCurriculumWithParams(params);
    }

    public Curriculum getCurriculum(String cardNumber, String term) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("cardNumber", cardNumber);
        params.add("term", term);
        return getCurriculumWithParams(params);
    }

    private Curriculum getCurriculumWithParams(
            MultivaluedMap<String, String> params) throws ServiceException {
        try {
            String response = resource.queryParams(params)
                    .accept(MediaType.APPLICATION_XML_TYPE)
                    .get(String.class);
            StringReader reader = new StringReader(response);
            JAXBContext jaxbContext = JAXBContext.newInstance(Curriculum.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Curriculum) jaxbUnmarshaller.unmarshal(reader);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
