package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import cn.edu.seu.herald.ws.api.library.Book;
import cn.edu.seu.herald.ws.api.library.Booklist;
import cn.edu.seu.herald.ws.api.library.CopiesType;
import cn.edu.seu.herald.ws.api.library.RenewalType;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class BooklistUnmarshaller extends AbstractUnmarshaller<Booklist> {

    @Override
    public Booklist unmarshall(InputStream xmlStream)
            throws UnmarshallerException {
        SAXReader reader = newSAXReader();
        try {
            Document document = reader.read(xmlStream);
            List<Node> bookNodes = selectNodes(document,
                    "/lib:booklist/lib:book");
            Booklist booklist = new Booklist();
            for (Node bookNode : bookNodes) {
                String name = selectSingleNodeText(bookNode, "lib:name");
                String author = selectSingleNodeText(bookNode, "lib:author");
                String press = selectSingleNodeNullableText(bookNode,
                        "lib:press");
                String isbn = selectSingleNodeNullableText(bookNode,
                        "lib:isbn");
                String href = selectSingleNodeNullableText(bookNode,
                        "@href");
                Book book = new Book();
                book.setName(name);
                book.setAuthor(author);
                book.setPress(press);
                book.setIsbn(isbn);
                book.setHref(href);
                book.setCopies(new CopiesType());
                book.setRenewal(new RenewalType());
                booklist.getBooks().add(book);
            }
            return booklist;
        } catch (Exception ex) {
            throw new UnmarshallerException(ex);
        }
    }
}
