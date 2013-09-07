package cn.edu.seu.herald.ws.api.impl;

import cn.edu.seu.herald.ws.api.curriculum.Curriculum;
import cn.edu.seu.herald.ws.api.impl.parser.*;
import cn.edu.seu.herald.ws.api.library.Book;
import cn.edu.seu.herald.ws.api.library.Booklist;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Dom4jXmlObjectUnmarshaller implements XmlObjectUnmarshaller {

    private static final Map<Class<?>, Unmarshaller<?>> unmarshallerMap =
            new HashMap<Class<?>, Unmarshaller<?>>();
    static {
        unmarshallerMap.put(Curriculum.class, new CurriculumUnmarshaller());
        unmarshallerMap.put(Runtime.class, new ExerciseUnmarshaller());
        unmarshallerMap.put(Booklist.class, new BooklistUnmarshaller());
        unmarshallerMap.put(Book.class, new BookUnmarshaller());
    }

    @Override
    public <T> T unmarshal(InputStream xmlStream, Class<T> jaxbClass)
            throws UnmarshallerException {
        Unmarshaller<?> unmarshaller = unmarshallerMap.get(jaxbClass);
        if (unmarshaller == null) {
            throw new UnmarshallerException(String.format(
                    "Unmarshaller<%s> is not found.", jaxbClass.getName()));
        }
        try {
            return (T) unmarshaller.unmarshall(xmlStream);
        } finally {
            safeCloseInputStream(xmlStream);
        }
    }

    private void safeCloseInputStream(InputStream inputStream)
            throws UnmarshallerException {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            throw new UnmarshallerException(ex);
        }
    }
}
