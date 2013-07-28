package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.MorningExerciseService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.exercise.RunTimesData;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class MorningExerciseServiceImpl extends AbstractXmlService
        implements MorningExerciseService {

    private static final String EXERCISE_PATH = "/exercise";
    private static final String REMAIN_DAYS_PATH = EXERCISE_PATH + "/remain";
    private static final String RUNTIME_PATH = EXERCISE_PATH + "/runtime";
    private final String baseResourceUri;

    public MorningExerciseServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public int getRemainDays() throws ServiceException {
        try {
            UriBuilder builder = UriBuilder.fromPath(
                    baseResourceUri + REMAIN_DAYS_PATH);
            WebResource resource = getWebResource(builder.build());
            String daysStr = resource.accept(MediaType.TEXT_PLAIN_TYPE)
                    .get(String.class);
            return Integer.parseInt(daysStr);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public RunTimesData getRunTimesData(String username, String password)
            throws ServiceException {
        UriBuilder builder = UriBuilder.fromPath(baseResourceUri + RUNTIME_PATH)
                .queryParam("username", username)
                .queryParam("password", password);
        URI uri = builder.build();
        return getJaxbObjectByResource(uri, RunTimesData.class);
    }
}
