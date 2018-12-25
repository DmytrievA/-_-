package com.lien;

import java.net.URL;
import java.util.Scanner;
import java.net.MalformedURLException;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.itroi.*;


public class TaskManagerClient {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException{
		URL url = new URL("http://localhost:1986/taskmanager?wsdl");
		QName qname = new QName("http://itroi.com/" ,"TaskManagerImpService");
		Service service = Service.create(url, qname);
		TaskManager manager = service.getPort(TaskManager.class);
		int taskNum = 1;
		while(taskNum!=0)
		{
			System.out.println("¬ыбарите метод:");
			System.out.println("1-очистить базу заданий");
			System.out.println("2-добавить новое задание");
			System.out.println("3-обновить задание по id");
			System.out.println("4-удалить задание по id");
			System.out.println("5-показать все существующие задани€");
			System.out.println("0-завершить работу");
			
			Scanner scan = new Scanner(System.in);
			taskNum = scan.nextInt();
			switch (taskNum){
			case 1:
				manager.dropTask();
				break;
			case 2:
				System.out.println("¬ведите им€ пользовател€ нового задани€:");
				String[] vars = new String[5];
				vars[0] = scan.nextLine();
				System.out.println("¬ведите название нового задани€:");
				vars[1] = scan.nextLine();
				System.out.println("¬ведите описание нового задани€:");
				vars[2] = scan.nextLine();
				System.out.println("¬ведите дату начала нового задани€: ");
				vars[3] = scan.nextLine();
				System.out.println("¬ведите дату конца нового задани€: ");
				vars[4] = scan.nextLine();
				System.out.println(manager.addTask(vars[0], vars[1], vars[2], vars[3], vars[4]));
				taskNum = 1;
				break;
			case 3:
				System.out.println("¬ведите id задани€, которое хотите помен€ть:");
				taskNum = scan.nextInt();
				System.out.println("¬ведите новое описание:");
				vars = new String[3];
				vars[0] = scan.nextLine();
				System.out.println("¬ведите новую дату начала: ");
				vars[1] = scan.nextLine();
				System.out.println("¬ведите новую дату конца: ");
				vars[2] = scan.nextLine();
				System.out.println(manager.changeTaskById(taskNum, vars[0], vars[1], vars[2]));
				taskNum = 1;
				break;
			case 4:
				System.out.println("¬ведите id задани€, которое хотите удалить:");
				taskNum = scan.nextInt();
				System.out.println(manager.deleteTaskById(taskNum));
				taskNum = 1;
				break;
			case 5:
				System.out.println(manager.allTask());
				taskNum = 1;
				break;
			default:
				System.out.println("¬ведЄн неправильный номер задани€");
				taskNum =1;
				break;
			}
		}
	}
}
