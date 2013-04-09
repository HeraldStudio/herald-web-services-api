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

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public interface CurriculumService {

    /**
     * 根据一卡通号获取本学期的课程表
     * @param cardNumber 学生的一卡通号
     * @return 本学期的课程表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Curriculum
     */
    Curriculum getCurriculum(String cardNumber) throws ServiceException;

    /**
     * 根据一卡通号、学期获取课程表
     * @param cardNumber 学生的一卡通号
     * @param term 学期，如11-12-3
     * @return 课程表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Curriculum
     */
    Curriculum getCurriculum(String cardNumber, String term)
            throws ServiceException;

    /**
     * 根据一卡通号获取本学期的时间表
     * @param cardNumber 学生的一卡通号
     * @return 时间表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.TimeTable
     */
    TimeTable getTimeTable(String cardNumber) throws ServiceException;

    /**
     * 根据一卡通号、学期获取时间表
     * @param cardNumber 学生的一卡通号
     * @param term 学期，如11-12-3
     * @return 时间表
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.TimeTable
     */
    TimeTable getTimeTable(String cardNumber, String term)
            throws ServiceException;

    /**
     * 根据一卡通号获取今天的课程行程
     * @param cardNumber 学生的一卡通号
     * @return 今天的行程
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Schedule
     */
    Schedule getSchedule(String cardNumber) throws ServiceException;

    /**
     * 根据一卡通、星期几获取课程行程
     * @param cardNumber 学生的一卡通号
     * @param day 星期几
     * @return 对应星期的行程
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Schedule
     */
    Schedule getSchedule(String cardNumber, Day day) throws ServiceException;

    /**
     * 根据一卡通、学期、星期几获取课程行程
     * @param cardNumber 学生的一卡通号
     * @param term 学期，如11-12-3
     * @param day 星期几
     * @return 对应学期、星期的行程
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Schedule
     */
    Schedule getSchedule(String cardNumber, String term, Day day)
            throws ServiceException;

    /**
     * 根据一卡通号获取下一节要上的课
     * @param cardNumber 学生的一卡通号
     * @return 下一节要上的课
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.Attendance
     */
    Attendance getNextAttendance(String cardNumber) throws ServiceException;
}
