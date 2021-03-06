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

import cn.edu.seu.herald.ws.api.curriculum.Attendance;
import cn.edu.seu.herald.ws.api.curriculum.Curriculum;
import cn.edu.seu.herald.ws.api.CurriculumService;
import cn.edu.seu.herald.ws.api.curriculum.Day;
import cn.edu.seu.herald.ws.api.curriculum.Period;
import cn.edu.seu.herald.ws.api.curriculum.Schedule;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.curriculum.TimeTable;
import org.apache.commons.httpclient.HttpClient;

import java.net.URI;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ws.rs.core.UriBuilder;

/**
 * 课程表服务的实现类，基于jersey实现的RESTful Web Services的客户端。
 *
 * @author rAy <predator.ray@gmail.com>
 */
class CurriculumServiceImpl extends AbstractXmlService
        implements CurriculumService {

    private static final String CURR_TMPLT = "/curriculum";
    private final String baseResourceUri;

    public CurriculumServiceImpl(
            RequestGetMethodFactory requestGetMethodFactory,
            HttpClient httpClient, String baseResourceUri) {
        super(requestGetMethodFactory, httpClient);
        this.baseResourceUri = baseResourceUri;
    }

    public Curriculum getCurriculum(String cardNumber) throws ServiceException {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(CURR_TMPLT).queryParam("cardNumber", cardNumber);
        URI uri = builder.build(cardNumber);
        return getJaxbObjectByResource(uri, Curriculum.class);
    }

    public Curriculum getCurriculum(String cardNumber, String term) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(CURR_TMPLT)
                .queryParam("cardNumber", cardNumber)
                .queryParam("term", term);
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
            int from = period.getFrom().intValue();
            if (from > startFrom) {
                return attendance;
            }
        }
        return null;
    }

    private static class AttendanceComparator
            implements Comparator<Attendance> {

        public int compare(Attendance o1, Attendance o2) {
            return o1.getPeriod().getFrom().intValue()
                    - o2.getPeriod().getTo().intValue();
        }
    }

    private static int getWhereToStartFrom() {
        Calendar now = Calendar.getInstance();
        int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
        int minuteOfDay = now.get(Calendar.MINUTE);
        return CoursePeriodUtils.getWhereToStartFrom(hourOfDay, minuteOfDay);
    }
}
