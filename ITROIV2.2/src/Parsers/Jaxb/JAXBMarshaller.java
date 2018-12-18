package Parsers.Jaxb;

import org.itroi.group.GroupType;
import org.itroi.reminder.ReminderType;
import org.itroi.task.GivenTask;
import org.itroi.task.RemindersType;
import org.itroi.task.TaskType;
import org.itroi.task.UserType;
import org.itroi.tasks.TasksType;

import Parsers.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JAXBMarshaller {

    public void marshal(TasksType tasks, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(TasksType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/tasks ../XML/tasks.xsd");
            marshalMeth(m,tasks,filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void marshal(GroupType groupType, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(GroupType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/group ../XML/Groups.xsd");
            marshalMeth(m,groupType,filePath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void marshal(ReminderType reminder, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(ReminderType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/reminder ../XML/Reminder.xsd");
            marshalMeth(m,reminder,filePath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void marshal(RemindersType reminders, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(RemindersType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/task ../XML/task.xsd");
            marshalMeth(m,reminders,filePath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void marshal(TaskType task, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(TaskType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/task ../XML/task.xsd");
            marshalMeth(m,task,filePath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void marshal(org.itroi.user.UserType user, String filePath)  {

        try {
            JAXBContext jc = JAXBContext.newInstance(org.itroi.user.UserType.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "http://www.itroi.org/user ../../XML/User.xsd");
            marshalMeth(m,user,filePath);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

public void marshalMeth(Marshaller m,Object value, String filePath) throws IOException{
    OutputStream os = null;
    try {
        os = new FileOutputStream(filePath);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(value, os);
        m.marshal(value, System.out);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        os.close();
    }

}
    public static void main(String[] arg) throws Exception {
        JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
        jaxbMarshaller.marshal(Util.createUserUser(), "src/jaxb.xml");
    }
}
