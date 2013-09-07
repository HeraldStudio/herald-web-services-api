package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import cn.edu.seu.herald.ws.api.update.Update;

import java.io.InputStream;

public class UpdateMarshaller extends AbstractUnmarshaller<Update> {

    @Override
    public Update unmarshall(InputStream xmlStream)
            throws UnmarshallerException {
        return null;
    }
}
