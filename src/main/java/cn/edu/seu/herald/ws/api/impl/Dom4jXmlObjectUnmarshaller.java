package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.curriculum.Curriculum;
import cn.edu.seu.herald.ws.api.impl.parser.CurriculumUnmarshaller;
import cn.edu.seu.herald.ws.api.impl.parser.Unmarshaller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Dom4jXmlObjectUnmarshaller implements XmlObjectUnmarshaller {

    private static final Map<Class<?>, Unmarshaller<?>> unmarshallerMap =
            new HashMap<Class<?>, Unmarshaller<?>>();
    static {
        unmarshallerMap.put(Curriculum.class, new CurriculumUnmarshaller());
    }

    @Override
    public <T> T unmarshal(InputStream xmlStream, Class<T> jaxbClass)
            throws UnmarshallerException {
        Unmarshaller<?> unmarshaller = unmarshallerMap.get(jaxbClass);
        if (unmarshaller == null) {
            throw new UnmarshallerException(String.format(
                    "Unmarshaller<%s> is not found.", jaxbClass.getName()));
        }
        return (T) unmarshaller.unmarshall(xmlStream);
    }
}
