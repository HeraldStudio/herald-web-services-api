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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 上课类，描述了学生在某个特点时间段应该上的课。
 * @author rAy <predator.ray@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "attendance")
@XmlType(name = "attendanceType")
public class Attendance {

    @XmlAttribute
    private String courseName;
    @XmlAttribute
    private String place;
    @XmlAttribute
    private StrategyType strategy;
    @XmlElement
    private Period period;

    /**
     * 获取所需上课的名称，该名称是对于
     * <code>cn.edu.seu.herald.ws.api.Course.getName()</code>
     * 的引用，并与之一一对应。
     * @see cn.edu.seu.herald.ws.api.Course#getName()
     * @return 所需上课的名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 返回上课的节数。是一个时间范围。例如从3~4节。
     * @see cn.edu.seu.herald.ws.api.Period
     * @return 上课的节数
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * 返回上课的地点。如，九龙湖教八-301
     * @return 上课的地点
     */
    public String getPlace() {
        return place;
    }

    /**
     * 返回上课策略，即单双周。
     * @see cn.edu.seu.herald.ws.api.StrategyType
     * @return 上课单双周策略
     */
    public StrategyType getStrategy() {
        return strategy;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setStrategy(StrategyType strategy) {
        this.strategy = strategy;
    }
}
