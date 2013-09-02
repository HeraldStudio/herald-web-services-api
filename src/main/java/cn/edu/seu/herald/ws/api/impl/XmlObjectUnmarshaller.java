package cn.edu.seu.herald.ws.api.impl;

import java.io.InputStream;

public interface XmlObjectUnmarshaller {

    <T> T unmarshal(InputStream xmlStream, Class<T> jaxbClass)
            throws UnmarshallerException;
}
