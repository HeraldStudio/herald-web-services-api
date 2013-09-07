package cn.edu.seu.herald.ws.api.impl.parser;

import cn.edu.seu.herald.ws.api.impl.UnmarshallerException;
import cn.edu.seu.herald.ws.api.library.Book;
import cn.edu.seu.herald.ws.api.library.CopiesType;
import cn.edu.seu.herald.ws.api.library.RenewalType;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class BookUnmarshaller extends AbstractUnmarshaller<Book> {

    @Override
    public Book unmarshall(InputStream xmlStream) throws UnmarshallerException {
        SAXReader reader = newSAXReader();
        try {
            Document document = reader.read(xmlStream);
            String name = selectSingleNodeText(document, "/lib:book/lib:name");
            String author = selectSingleNodeText(document,
                    "/lib:book/lib:author");
            String press = selectSingleNodeNullableText(document,
                    "/lib:book/lib:press");
            String isbn = selectSingleNodeNullableText(document,
                    "/lib:book/lib:isbn");
            String href = selectSingleNodeNullableText(document,
                    "/lib:book/@href");
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPress(press);
            book.setIsbn(isbn);
            book.setHref(href);
            book.setCopies(new CopiesType());
            book.setRenewal(new RenewalType());
            return book;
        } catch (Exception ex) {
            throw new UnmarshallerException(ex);
        }
    }
}
