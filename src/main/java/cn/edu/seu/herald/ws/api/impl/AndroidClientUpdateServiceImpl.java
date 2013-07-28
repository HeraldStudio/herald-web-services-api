package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.AndroidClientUpdateService;
import cn.edu.seu.herald.ws.api.ServiceException;
import cn.edu.seu.herald.ws.api.update.Update;

import javax.ws.rs.core.UriBuilder;

/**
 * Copyright (c) 2013 Ray <predator.ray@gmail.com>
 */
class AndroidClientUpdateServiceImpl extends AbstractXmlService
        implements AndroidClientUpdateService {

    private static final String UPDATE_PATH = "/update";
    private String baseResourceUri;

    public AndroidClientUpdateServiceImpl(String baseResourceUri) {
        this.baseResourceUri = baseResourceUri;
    }

    @Override
    public Update getNewVersion() throws ServiceException {
        UriBuilder builder = UriBuilder.fromPath(baseResourceUri + UPDATE_PATH);
        return getJaxbObjectByResource(builder.build(), Update.class);
    }
}
