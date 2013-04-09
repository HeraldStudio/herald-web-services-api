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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 课程类，包含课程名称、讲师、学分、上课周次区间。
 * @author rAy <predator.ray@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "course")
@XmlType(name = "courseType")
public class Course {

    @XmlElement
    private String name;
    @XmlElement
    private String lecturer;
    @XmlElement
    private double credit;
    @XmlElement
    private Period week;

    /**
     * 返回课程的名称
     * @return 课程的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 返回课程讲师的姓名
     * @return 讲师
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * 返回课程的学分，学分以浮点数表示。
     * @return 学分
     */
    public double getCredit() {
        return credit;
    }

    /**
     * 返回课程的上课周次，如1~16周
     * @see cn.edu.seu.herald.ws.api.Period
     * @return 上课周次
     */
    public Period getWeek() {
        return week;
    }

    void setName(String name) {
        this.name = name;
    }

    void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    void setCredit(double credit) {
        this.credit = credit;
    }

    void setWeek(Period week) {
        this.week = week;
    }
}
