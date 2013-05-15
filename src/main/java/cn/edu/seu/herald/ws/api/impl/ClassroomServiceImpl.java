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

import cn.edu.seu.herald.ws.api.ClassroomService;
import cn.edu.seu.herald.ws.api.curriculum.Day;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

/**
 * 教室服务的实现类，基于jersey实现的RESTful Web Services的客户端。
 * @author rAy <predator.ray@gmail.com>
 */
class ClassroomServiceImpl extends AbstractCsvService
        implements ClassroomService {

    private static final String CLASSROOM_UNUSED_PATH = "/classroom/unused";
    private String baseResourceUri;

    public ClassroomServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    public String[] getClassroomUnused(Day day, int from, int to) {
        UriBuilder builder = UriBuilder.fromUri(baseResourceUri)
                .path(CLASSROOM_UNUSED_PATH)
                .queryParam("day", day)
                .queryParam("from", from).queryParam("to", to);
        URI uri = builder.build(day);
        return getCsvByResouse(uri).get(0);
    }
}
