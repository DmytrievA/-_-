package Parsers.DOM;

import org.itroi.task.TaskType;
import org.itroi.task.UserType;
import org.itroi.tasks.TasksType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomUnmarshaller  {

    private static final String BS_NS = "http://nure.ua/bookShop";

    public static void main(String[] arg) {
        BookUnmarshaller parser = new DomUnmarshaller();
        BookShop bookShop = parser.unmarshal("src/main/resources/xml/bookShop.xml");
        System.out.println(bookShop);
    }


    public TasksType unmarshal(String filePath) {
        TasksType tasks = new TasksType();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            if (doc != null) {
                Element tasksElement = doc.getDocumentElement();
                if (tasksElement != null) {
                    NodeList tasksNodeList = tasksElement.getChildNodes();
                    for (int i = 0; i < tasksNodeList.getLength(); i++) {
                        if (tasksNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                            TaskType task = parseTask((Element) tasksNodeList.item(i));
                            if (task != null) {
                                tasks.getTask().add(task);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private TaskType parseTask(Element TaskElement) {
        TaskType task = new TaskType();
        task.setId(Integer.parseInt(TaskElement.getAttribute("id")));
        task.setTitle(getValue(TaskElement, "title"));
        task.setTime(Integer.parseInt(getValue(TaskElement,"time")));
        NodeList nodeList = TaskElement.getElementsByTagName("user");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        TaskType task = parseTask((Element) nodeList.item(i));
                        if (task != null) {
                            tasks.getTask().add(task);
                        }
                    }
                }
            }
        }
        task.setPrice(Double.parseDouble(getValue(TaskElement, "price")));
        task.setCategory(Category.fromValue(getValue(TaskElement, "category")));
        return book;
    }
private UserType parseUser(Element TaskElement){
        UserType userType =new UserType();
        userType.setEmail();
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
}

