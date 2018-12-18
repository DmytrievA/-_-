package Parsers;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.itroi.group.GroupType;
import org.itroi.reminder.ReminderType;
import org.itroi.task.*;
import org.itroi.tasks.TasksType;
import org.itroi.user.GroupRole;
import org.itroi.user.MemberOfGroup;
import org.itroi.user.MemberOfGroups;

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
    public static TaskType createTesk() {
        TaskType task = new TaskType();
        return task;
    }

    public static GivenTask createGivenTask(){
        GivenTask givenTask = new GivenTask();
        return givenTask;
    }

    public static org.itroi.user.UserType createUserUser(){
        org.itroi.user.UserType userType = new org.itroi.user.UserType("1@com.ua","name","surname",
                "string@mail.com","loginefw","password","male",createMemberOfGroups(),"user");
        return userType;
    }

    public static UserType createUserTask(){
        UserType userType = new UserType();
        return userType;
    }

    public static StatusType createStatusType(){
        StatusType statusType = new StatusType();
        return statusType;
    }

    public static RateLevel createRateLevel()
    {
        RateLevel rateLevel = new RateLevel();
        return rateLevel;
    }

    public static RemindersType createReminders(){
        RemindersType remindersType = new RemindersType();
        return remindersType;
    }

    public static ReminderType createReminder(){
        ReminderType reminderType = new ReminderType();
        return reminderType;
    }

    public static TaskType createTask(){
        TaskType taskType = new TaskType();
        return taskType;
    }

    public static GroupRole createGroupRole(){
        GroupRole groupRole = new GroupRole(1,"pidor");
        return groupRole;
    }

    public static MemberOfGroup createMemberOfGroup(){
        MemberOfGroup memberOfGroup = new MemberOfGroup(createGroup(),createGroupRole());
        return memberOfGroup;
    }
    public static MemberOfGroups createMemberOfGroups(){
        MemberOfGroups memberOfGroups = new MemberOfGroups();
        memberOfGroups.getMemberOfGroup().add(createMemberOfGroup());

        return memberOfGroups;
    }

    public static GroupType createGroup(){
        GroupType groupType = new GroupType(1,"loxfweewg");
        return groupType;
    }
}
