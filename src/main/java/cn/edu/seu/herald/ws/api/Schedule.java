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
package cn.edu.seu.herald.ws.api;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 描述某一天的课程行程
 * @author rAy <predator.ray@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scheduleType")
public class Schedule {

    @XmlAttribute
    private Day day;
    @XmlElement(name = "attendance")
    private List<Attendance> attendances;

    /**
     * 返回这是星期几的课程行程
     * @see cn.edu.seu.herald.ws.api.Day
     * @return 星期几
     */
    public Day getDay() {
        return day;
    }

    /**
     * 返回这天需要上的课程已经对应的时间地点。
     * @see cn.edu.seu.herald.ws.api.Attendance
     * @return 学生在某个特点时间段应该上的课的列表
     */
    public List<Attendance> getAttendances() {
        return attendances;
    }

    void setDay(Day day) {
        this.day = day;
    }

    void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
