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

import cn.edu.seu.herald.ws.api.Attendance;
import cn.edu.seu.herald.ws.api.Curriculum;
import cn.edu.seu.herald.ws.api.CurriculumService;
import cn.edu.seu.herald.ws.api.Day;
import cn.edu.seu.herald.ws.api.Schedule;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.TimeTable;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * 课程表服务的实现类，基于jersey实现的RESTful Web Services的客户端。
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class CurriculumServiceImpl implements CurriculumService {

    private static final String CURR_TMPLT_1 = "/curriculum;cardNumber={1}";
    private static final String CURR_TMPLT_2 =
            "/curriculum;cardNumber={1};term={2}";
    private static final String TIMETBL_TMPLT = "/timeTable";
    private static final String SCHED_TMPLT_1 = "/schedule";
    private static final String SCHED_TMPLT_2 = "/schedule;day={3}";
    private static final String ATTEND_TMPLT = "/attendence";
    private final String baseResourceUri;
    private Client client;

    public CurriculumServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
        client = Client.create();
    }

    public Curriculum getCurriculum(String cardNumber) throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_1);
        URI uri = builder.build(cardNumber);
        return getJaxbObjectByResource(uri, Curriculum.class);
    }

    public Curriculum getCurriculum(String cardNumber, String term) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_2);
        URI uri = builder.build(cardNumber, term);
        return getJaxbObjectByResource(uri, Curriculum.class);
    }

    public TimeTable getTimeTable(String cardNumber) throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_1 + TIMETBL_TMPLT);
        URI uri = builder.build(cardNumber);
        return getJaxbObjectByResource(uri, TimeTable.class);
    }

    public TimeTable getTimeTable(String cardNumber, String term) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_2 + TIMETBL_TMPLT);
        URI uri = builder.build(cardNumber, term);
        return getJaxbObjectByResource(uri, TimeTable.class);
    }

    public Schedule getSchedule(String cardNumber) throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_1 + TIMETBL_TMPLT + SCHED_TMPLT_1);
        URI uri = builder.build(cardNumber);
        return getJaxbObjectByResource(uri, Schedule.class);
    }

    public Schedule getSchedule(String cardNumber, Day day)
            throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_1 + TIMETBL_TMPLT + SCHED_TMPLT_2);
        URI uri = builder.build(cardNumber, day);
        return getJaxbObjectByResource(uri, Schedule.class);
    }

    public Schedule getSchedule(String cardNumber, String term, Day day)
            throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_2 + TIMETBL_TMPLT + SCHED_TMPLT_2);
        URI uri = builder.build(cardNumber, term, day);
        return getJaxbObjectByResource(uri, Schedule.class);
    }

    public Attendance getNextAttendance(String cardNumber)
            throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri);
        builder.path(CURR_TMPLT_1 + TIMETBL_TMPLT + SCHED_TMPLT_1 +
                ATTEND_TMPLT);
        URI uri = builder.build(cardNumber);
        return getJaxbObjectByResource(uri, Attendance.class);
    }

    private <T> T getJaxbObjectByResource(URI uri, Class<T> clz)
            throws ServiceException {
        try {
            WebResource resource = client.resource(uri);
            return resource.accept(MediaType.APPLICATION_XML_TYPE).get(clz);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }
}
