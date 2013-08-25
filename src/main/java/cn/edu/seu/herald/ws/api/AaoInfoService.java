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

import org.apache.wink.common.model.rss.RssFeed;

/**
 * 校园信息服务接口
 * @author rAy <predator.ray@gmail.com>
 */
public interface AaoInfoService extends ConfigurableService {

    /**
     * 获取教务处的信息RSS供稿
     * @param name 教务子分类名称
     * @param limit 返回供稿的大小限制
     * @return 教务处的信息供稿。若没有更新，则返回<code>null</code>
     */
    RssFeed getAaoRssFeed(String name, int limit);

    /**
     * 获取教务处的信息某条目之前的RSS供稿
     * @param name 教务子分类名称
     * @param uuid 条目的uuid(guid)
     * @param limit 返回供稿的大小限制
     * @return 教务处的信息供稿。若没有更新，则返回<code>null</code>
     */
    RssFeed getAaoRssFeedBefore(String name, String uuid, int limit);


    /**
     * 获取教务处的信息某条目之后的RSS供稿
     * @param name 教务子分类名称
     * @param uuid 条目的uuid(guid)
     * @param limit 返回供稿的大小限制
     * @return 教务处的信息供稿。若没有更新，则返回<code>null</code>
     */
    RssFeed getAaoRssFeedAfter(String name, String uuid, int limit);
}
