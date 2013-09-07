package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.util.Assert;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.Collections;
import java.util.List;

abstract class AbstractUnmarshaller<T> implements Unmarshaller<T> {

    private SAXReaderFactory saxReaderFactory = new SAXReaderFactory();

    protected SAXReader newSAXReader() {
        return saxReaderFactory.newSAXReader();
    }

    protected String selectSingleNodeText(Node rootNode, String xpath)
            throws IllegalArgumentException {
        Node node = rootNode.selectSingleNode(xpath);
        Assert.notNull(node, String.format("Node [%s] is not found.", xpath));
        return node.getText();
    }

    protected String selectSingleNodeNullableText(Node rootNode, String xpath)
            throws IllegalArgumentException {
        Node node = rootNode.selectSingleNode(xpath);
        return (node == null) ? null : node.getText();
    }

    protected List<String> selectTextNodes(Node rootNode, String xpath) {
        List<String> nodes = rootNode.selectNodes(xpath);
        return (nodes == null) ? Collections.<String>emptyList() : nodes;
    }

    protected List<Node> selectNodes(Node rootNode, String xpath) {
        List<Node> nodes = rootNode.selectNodes(xpath);
        return (nodes == null) ? Collections.<Node>emptyList() : nodes;
    }
}
