package Parsers.Jaxb;

import org.itroi.tasks.TasksType;

import Parsers.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JAXBMarshaller {

    public void marshal(TasksType tasks, String filePath) throws IOException {
        OutputStream os = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(TasksType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/tasks ../XML/tasks.xsd");
            os = new FileOutputStream(filePath);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(tasks, os);
            m.marshal(tasks, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    public static void main(String[] arg) throws Exception {
        JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
        jaxbMarshaller.marshal(Util.createTasks(), "src/jaxb.xml");
    }
}
