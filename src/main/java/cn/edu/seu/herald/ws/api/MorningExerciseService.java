package cn.edu.seu.herald.ws.api;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
public interface MorningExerciseService {

    int getRemainDays() throws ServiceException;

    Object getRunBroadcast() throws ServiceException;

    Object getRunTimesData() throws ServiceException;

    Object getExerciseInfo(String username, String password)
            throws ServiceException;
}
