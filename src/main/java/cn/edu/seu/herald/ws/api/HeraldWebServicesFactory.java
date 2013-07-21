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
 * 先声网Web服务抽象工厂接口
 * @author rAy <predator.ray@gmail.com>
 */
public interface HeraldWebServicesFactory {

    /**
     * 获取课程表服务
     * @return 课程表服务
     */
    CurriculumService getCurriculumService();

    /**
     * 获取教务处服务
     * @return 教务处服务
     */
    AaoInfoService getCampusInfoService();

    /**
     * 获取教室服务
     * @return 教室服务
     */
    ClassroomService getClassroomService();

    /**
     * 获取图书馆服务
     * @return 图书馆服务
     */
    LibraryService getLibraryService();

    /**
     * 获取安卓客户端更新服务
     * @return 安卓客户端更新服务
     */
    AndroidClientUpdateService getAndroidClientUpdateService();

    /**
     * 获取跑操服务
     * @return 跑操服务
     */
    MorningExerciseService getMorningExerciseService();
}
