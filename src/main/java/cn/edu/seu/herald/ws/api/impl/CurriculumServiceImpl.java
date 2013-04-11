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
import cn.edu.seu.herald.ws.api.Period;
import cn.edu.seu.herald.ws.api.Schedule;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.TimeTable;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
        Curriculum curriculum = getCurriculum(cardNumber);
        return curriculum.getTimeTable();
    }

    public TimeTable getTimeTable(String cardNumber, String term) {
        Curriculum curriculum = getCurriculum(cardNumber, term);
        return curriculum.getTimeTable();
    }

    public Schedule getSchedule(String cardNumber) throws ServiceException {
        return getSchedule(cardNumber, Day.getDayOfToday());
    }

    public Schedule getSchedule(String cardNumber, Day day)
            throws ServiceException {
        TimeTable timeTable = getTimeTable(cardNumber);
        return searchScheduleWithDay(timeTable, day);
    }

    public Schedule getSchedule(String cardNumber, String term, Day day)
            throws ServiceException {
        TimeTable timeTable = getTimeTable(cardNumber, term);
        return searchScheduleWithDay(timeTable, day);
    }

    private Schedule searchScheduleWithDay(TimeTable timeTable, Day day) {
        for (Schedule schedule : timeTable.getSchedules()) {
            if (schedule.getDay().equals(day)) {
                return schedule;
            }
        }
        return null;
    }

    public Attendance getNextAttendance(String cardNumber)
            throws ServiceException {
        Schedule scheduleOfToday = getSchedule(cardNumber);
        int startFrom = getWhereToStartFrom();
        List<Attendance> toBeOrdered = scheduleOfToday.getAttendances();
        Collections.sort(toBeOrdered, new AttendanceComparator());
        for (Attendance attendance : toBeOrdered) {
            Period period = attendance.getPeriod();
            int from = period.getFrom();
            if (from > startFrom) {
                return attendance;
            }
        }
        return null;
    }

    private static class AttendanceComparator
            implements Comparator<Attendance> {

        public int compare(Attendance o1, Attendance o2) {
            return o1.getPeriod().getFrom() - o2.getPeriod().getTo();
        }
    }

    private static int getWhereToStartFrom() {
        Calendar now = Calendar.getInstance();
        int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
        int minuteOfDay = now.get(Calendar.MINUTE);
        return CoursePeriodUtils.getWhereToStartFrom(hourOfDay, minuteOfDay);
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
