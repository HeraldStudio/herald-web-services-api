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

import cn.edu.seu.herald.ws.api.*;

/**
 * 先声网Web服务抽象工厂的实现类，返回各产品的具体实现。
 * @author rAy <predator.ray@gmail.com>
 */
public class HeraldWebServicesFactoryImpl implements HeraldWebServicesFactory {

    private final String baseResourceUri;

    /**
     * 构造一个该工程的具体示例，需要传递服务的基础资源URI
     * @param baseResourceUri 基础资源URI
     */
    public HeraldWebServicesFactoryImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    /**
     * 返回课程表服务
     * @return 课程表服务
     */
    @Override
    public CurriculumService getCurriculumService() {
        return new CurriculumServiceImpl(baseResourceUri);
    }

    /**
     * 获取教务处服务
     * @return 教务处服务
     */
    @Override
    public AaoInfoService getCampusInfoService() {
        return new AaoInfoServiceImpl(baseResourceUri);
    }

    /**
     * 获取教室服务
     * @return 教室服务
     */
    @Override
    public ClassroomService getClassroomService() {
        return new ClassroomServiceImpl(baseResourceUri);
    }

    /**
     * 获取图书馆服务
     * @return 图书馆服务
     */
    @Override
    public LibraryService getLibraryService() {
        return new LibraryServiceImpl(baseResourceUri);
    }

    /**
     * 获取安卓客户端更新服务
     * @return 安卓客户端更新服务
     */
    @Override
    public AndroidClientUpdateService getAndroidClientUpdateService() {
        return new AndroidClientUpdateServiceImpl(baseResourceUri);
    }

    /**
     * 获取跑操服务
     * @return 跑操服务
     */
    @Override
    public MorningExerciseService getMorningExerciseService() {
        return new MorningExerciseServiceImpl(baseResourceUri);
    }
}
