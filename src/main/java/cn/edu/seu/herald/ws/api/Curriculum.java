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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 课程表类，描述一个学生某个学期的课程表，包含了课程以及课程的具体时间安排。
 * @author rAy <predator.ray@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "curriculum")
@XmlType(name = "curriculumType")
public class Curriculum {

    @XmlAttribute
    private String cardNumber;
    @XmlAttribute
    private String term;
    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    private List<Course> courses;
    @XmlElement(name = "timeTable")
    private TimeTable timeTable;

    /**
     * 该课程表对应学生的一卡通号
     * @return 一卡通号
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * 该课程表对应学生的学期，学期的表示形式以-为分隔符。
     * 例如11-12-3表示11至12学年第三学期。
     * @return 学期
     */
    public String getTerm() {
        return term;
    }

    /**
     * 获取所有的课程
     * @see cn.edu.seu.herald.ws.api.Course
     * @return 课程列表
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * 获取课程的时间安排
     * @see cn.edu.seu.herald.ws.api.TimeTable
     * @return 时间表
     */
    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
