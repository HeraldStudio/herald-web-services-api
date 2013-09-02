package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.util.Assert;
import org.dom4j.Node;

import java.util.List;

abstract class AbstractUnmarshaller<T> implements Unmarshaller<T> {

    protected String selectSingleNodeText(Node rootNode, String xpath)
            throws IllegalArgumentException {
        Node node = rootNode.selectSingleNode(xpath);
        Assert.notNull(node, String.format("Node [%s] is not found.", xpath));
        return node.getText();
    }

    protected List<Node> selectNodes(Node rootNode, String xpath) {
        List<Node> nodes = rootNode.selectNodes(xpath);
        Assert.notNull(nodes, String.format("Nodes [%s] are not found.",
                xpath));
        return nodes;
    }
}
