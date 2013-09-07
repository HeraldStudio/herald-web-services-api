package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import cn.edu.seu.herald.ws.api.library.User;

import java.io.InputStream;

public class UserMarshaller extends AbstractUnmarshaller<User> {

    @Override
    public User unmarshall(InputStream xmlStream) throws UnmarshallerException {
        return null;
    }
}
