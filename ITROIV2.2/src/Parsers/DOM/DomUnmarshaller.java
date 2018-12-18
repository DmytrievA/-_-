package Parsers.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/*
import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.entity.Category;
import ua.nure.bookshop.parser.BookUnmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomUnmarshaller implements BookUnmarshaller {

    private static final String BS_NS = "http://nure.ua/bookShop";

    public static void main(String[] arg) {
        BookUnmarshaller parser = new DomUnmarshaller();
        BookShop bookShop = parser.unmarshal("src/main/resources/xml/bookShop.xml");
        System.out.println(bookShop);
    }

    @Override
    public BookShop unmarshal(String filePath) {
        BookShop bookShop = new BookShop();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            if (doc != null) {
                Element bookShopElement = doc.getDocumentElement();
                if (bookShopElement != null) {
                    NodeList bookNodeList = bookShopElement.getChildNodes();
                    for (int i = 0; i < bookNodeList.getLength(); i++) {
                        if (bookNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                            Book book = parseBook((Element) bookNodeList.item(i));
                            if (book != null) {
                                bookShop.getBook().add(book);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookShop;
    }

    private Book parseBook(Element bookElement) {
        Book book = new Book();
        book.setId(Integer.parseInt(bookElement.getAttribute("id")));
        book.setTitle(getValue(bookElement, "title"));
        book.getAuthor().addAll(getValues(bookElement, "author"));
        book.setISBN(getValue(bookElement, "ISBN"));
        book.setPrice(Double.parseDouble(getValue(bookElement, "price")));
        book.setCategory(Category.fromValue(getValue(bookElement, "category")));
        return book;
    }

    private List<String> getValues(Element parent, String nodeName) {
        List<String> values = new ArrayList<>();
        NodeList elements = parent.getElementsByTagNameNS(BS_NS, nodeName);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            if (node != null) {
                values.add(node.getTextContent());
            } else {
                values.add("");
            }
        }
        return values;
    }

    private String getValue(Element parent, String nodeName) {
        return getValues(parent, nodeName).get(0);
    }
}*/

