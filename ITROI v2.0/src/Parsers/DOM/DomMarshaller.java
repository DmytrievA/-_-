package Parsers.DOM;

import Parsers.Util;
import javafx.concurrent.Task;
import org.itroi.reminder.ReminderType;
import org.itroi.task.TaskType;
import org.itroi.tasks.TasksType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;


public class DomMarshaller  {

    private static final String BS_NS = "http://www.itroi.org/tasks";
    private static final String BS_NS_Task = "http://www.itroi.org/task";
    private static final String BS_NS_User = "http://www.itroi.org/user";
    private static final String BS_NS_Reminder = "http://www.itroi.org/reminder";
    private static final String BS_NS_Group = "http://www.itroi.org/group";


    public static void main(String[] arg) throws IOException {
        DomMarshaller parser = new DomMarshaller();
        try {
            parser.marshal(Util.createTasks(), "src/dom.xml");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

    }


    public void marshal(TasksType tasks, String filePath) throws IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = sf.newSchema(new File("src/XML/Tasks.xsd"));
            factory.setSchema(schema);
            factory.setNamespaceAware(true);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element tasksElement = doc.createElementNS(BS_NS, "tasks");


            tasks.getTask().forEach(task -> {
                tasksElement.appendChild(getTaskElement(task, doc));
            });
            doc.appendChild(tasksElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element getTaskElement(TaskType task, Document doc) {
        Element TaskElement = doc.createElementNS(BS_NS, "task");
        TaskElement.setAttribute("id", String.valueOf(task.getId()));
        TaskElement.appendChild(getSimpleElement(doc, BS_NS_Task, "title", task.getTitle()));
        TaskElement.appendChild(getSimpleElement(doc, BS_NS_Task, "date", task.getDate()));
        TaskElement.appendChild(getSimpleElement(doc, BS_NS_Task, "time", task.getTime()));
        TaskElement.appendChild(getSimpleElement(doc, BS_NS_Task, "duration", task.getDuration()));
        TaskElement.appendChild(getSimpleElement(doc, BS_NS_Task, "description", task.getDescription()));
        Element StatusElement = doc.createElementNS(BS_NS_Task, "status");
        StatusElement.setAttribute("id", String.valueOf(task.getStatus().getId()));
        StatusElement.appendChild(getSimpleElement(doc,BS_NS_Task, "name",task.getStatus().getName()));
        TaskElement.appendChild(StatusElement);
        Element UserElement = doc.createElementNS(BS_NS_Task,"user");
        UserElement.setAttribute("email", task.getUser().getEmail());
        TaskElement.appendChild(UserElement);
        Element RemindersElement = doc.createElementNS(BS_NS_Task,"reminders");

        task.getReminders().getReminder().forEach(reminder ->
               RemindersElement.appendChild(getReminder(doc,BS_NS_Reminder,reminder))
                );
        TaskElement.appendChild(RemindersElement);
        return TaskElement;
    }

    private Element getSimpleElement(Document doc, String ns, String tagName, Object value) {
        Element element = doc.createElementNS(ns, tagName);
        element.setTextContent(String.valueOf(value));
        return element;
    }
private Element getReminder(Document doc, String ns, ReminderType rem){
        Element elem = doc.createElementNS(BS_NS_Task,"reminder");
        elem.setAttribute("id",String.valueOf(rem.getId()));
        elem.appendChild(getSimpleElement(doc,ns,"taskName",rem.getTaskName()));
        elem.appendChild(getSimpleElement(doc,ns,"time",rem.getTime()));
        elem.appendChild(getSimpleElement(doc,ns,"taskId",rem.getTaskId()));
        return elem;
}
}
