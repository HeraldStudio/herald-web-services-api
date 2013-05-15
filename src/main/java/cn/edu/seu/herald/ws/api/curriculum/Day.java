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
package cn.edu.seu.herald.ws.api.curriculum;

import java.util.Calendar;
import java.util.Date;

/**
 * 星期枚举
 *
 * @author rAy <predator.ray@gmail.com>
 */
public enum Day {

    /**
     * 星期一
     */
    MON,
    /**
     * 星期二
     */
    TUE,
    /**
     * 星期三
     */
    WED,
    /**
     * 星期四
     */
    THU,
    /**
     * 星期五
     */
    FRI,
    /**
     * 星期六
     */
    SAT,
    /**
     * 星期日
     */
    SUN;

    /**
     * 获取日期中的星期
     *
     * @param date 日期
     * @return 星期
     */
    public static Day getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return parseCalendarDay(dayOfWeek);
    }

    /**
     * 获取今天星期几
     * @return 星期
     */
    public static Day getDayOfToday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return parseCalendarDay(dayOfWeek);
    }

    private static Day parseCalendarDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return SUN;
            case Calendar.MONDAY:
                return MON;
            case Calendar.TUESDAY:
                return TUE;
            case Calendar.WEDNESDAY:
                return WED;
            case Calendar.THURSDAY:
                return THU;
            case Calendar.FRIDAY:
                return FRI;
            case Calendar.SATURDAY:
                return SAT;
            default:
                throw new RuntimeException("There is no Day for " + dayOfWeek);
        }
    }
}
