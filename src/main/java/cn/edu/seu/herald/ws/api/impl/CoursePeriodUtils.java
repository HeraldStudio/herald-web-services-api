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

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
class CoursePeriodUtils {

    private static final int[] TIME_POINTS = {
        8*60 + 00,
        8*60 + 50,
        9*60 + 45,
        10*60 + 35,
        11*60 + 30,
        14*60 + 00,
        14*60 + 50,
        15*60 + 45,
        16*60 + 35,
        17*60 + 30,
        18*60 + 30,
        19*60 + 20,
        20*60 + 15
    };

    public static int getWhereToStartFrom(int hour, int minute) {
        int minutesOfDay = hour * 60 + minute;
        for (int i = 0; i < TIME_POINTS.length; ++i) {
            int timePoint = TIME_POINTS[i];
            if (minutesOfDay < timePoint) {
                return i;
            }
        }
        return TIME_POINTS.length;
    }
}
