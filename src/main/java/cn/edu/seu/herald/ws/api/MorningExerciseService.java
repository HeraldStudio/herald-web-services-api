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

import cn.edu.seu.herald.ws.api.exercise.Broadcast;
import cn.edu.seu.herald.ws.api.exercise.RunTimesData;

/**
 * 跑擦服务接口
 * @author rAy <predator.ray@gmail.com>
 */
public interface MorningExerciseService {

    /**
     * 根据校历返回剩余跑操次数
     * @return 剩余跑操次数
     * @throws ServiceException
     */
    int getRemainDays() throws ServiceException;

    /**
     * 查询跑操播报，查询当天是否跑操
     * @return 跑操播报
     * @throws ServiceException
     * @see cn.edu.seu.herald.ws.api.exercise.Broadcast
     */
    Broadcast getRunBroadcast() throws ServiceException;

    /**
     * 查询当前用户跑操信息
     * @param username 体育系用户名
     * @param password 体育系密码
     * @return 跑操信息
     * @throws ServiceException
     */
    RunTimesData getRunTimesData(String username, String password)
            throws ServiceException;
}