package Parsers;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.itroi.reminder.ReminderType;
import org.itroi.task.*;
import org.itroi.tasks.TasksType;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class Util {
    public static TasksType createTasks() throws DatatypeConfigurationException {
       TasksType tasks = new TasksType();
       TaskType task = new TaskType();
       Date dbDate = new Date();
       long time = dbDate.getTime();
     StatusType status =new StatusType();
       status.setName("new");

        UserType user = new UserType();
        user.setEmail("strong@frk.com");

        RemindersType reminders = new RemindersType();
        ReminderType rem =new ReminderType();
        rem.setTaskId(1);
        rem.setTaskName("lol");
        rem.setId(2);
        rem.setTime(dbDate);
        reminders.addReminder(rem);
       task.setReminders(reminders);
        task.setDate(dbDate);
        task.setDuration(time);
        task.setTitle("lol");
        task.setId(1);
        task.setStatus(status);
        task.setUser(user);
        task.setDescription("lalaley");
       task.setUser(user);
       tasks.addTask(task);
        return tasks;
    }
}
