package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;

import java.io.InputStream;

public interface Unmarshaller<T> {

    T unmarshall(InputStream xmlStream) throws UnmarshallerException;
}
