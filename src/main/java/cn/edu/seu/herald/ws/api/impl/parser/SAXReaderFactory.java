package cn.edu.seu.herald.ws.api.impl.parser;

import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Map;

class SAXReaderFactory {

    private static final Map<String, String> nsPrefixes =
            new HashMap<String, String>();
    static {
        nsPrefixes.put("curr", "http://herald.seu.edu.cn/ws/curriculum");
        nsPrefixes.put("exer", "http://herald.seu.edu.cn/ws/morning-exercise");
        nsPrefixes.put("gpa", "http://herald.seu.edu.cn/ws/grade-point");
        nsPrefixes.put("lib", "http://herald.seu.edu.cn/ws/library");
        nsPrefixes.put("upd", "http://herald.seu.edu.cn/ws/android-update");
    }

    public SAXReader newSAXReader() {
        DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs(nsPrefixes);
        SAXReader saxReader = new SAXReader();
        saxReader.setDocumentFactory(factory);
        return saxReader;
    }
}
