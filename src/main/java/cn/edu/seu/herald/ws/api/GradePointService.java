package cn.edu.seu.herald.ws.api;

import cn.edu.seu.herald.ws.api.gpa.GradePoint;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public interface GradePointService {

    GradePoint getGradePoint(String username, String password);
}
