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
			System.out.println("�������� �����:");
			System.out.println("1-�������� ���� �������");
			System.out.println("2-�������� ����� �������");
			System.out.println("3-�������� ������� �� id");
			System.out.println("4-������� ������� �� id");
			System.out.println("5-�������� ��� ������������ �������");
			System.out.println("0-��������� ������");
			
			Scanner scan = new Scanner(System.in);
			taskNum = scan.nextInt();
			switch (taskNum){
			case 1:
				manager.dropTask();
				break;
			case 2:
				System.out.println("������� ��� ������������ ������ �������:");
				String[] vars = new String[5];
				vars[0] = scan.nextLine();
				System.out.println("������� �������� ������ �������:");
				vars[1] = scan.nextLine();
				System.out.println("������� �������� ������ �������:");
				vars[2] = scan.nextLine();
				System.out.println("������� ���� ������ ������ �������: ");
				vars[3] = scan.nextLine();
				System.out.println("������� ���� ����� ������ �������: ");
				vars[4] = scan.nextLine();
				System.out.println(manager.addTask(vars[0], vars[1], vars[2], vars[3], vars[4]));
				taskNum = 1;
				break;
			case 3:
				System.out.println("������� id �������, ������� ������ ��������:");
				taskNum = scan.nextInt();
				System.out.println("������� ����� ��������:");
				vars = new String[3];
				vars[0] = scan.nextLine();
				System.out.println("������� ����� ���� ������: ");
				vars[1] = scan.nextLine();
				System.out.println("������� ����� ���� �����: ");
				vars[2] = scan.nextLine();
				System.out.println(manager.changeTaskById(taskNum, vars[0], vars[1], vars[2]));
				taskNum = 1;
				break;
			case 4:
				System.out.println("������� id �������, ������� ������ �������:");
				taskNum = scan.nextInt();
				System.out.println(manager.deleteTaskById(taskNum));
				taskNum = 1;
				break;
			case 5:
				System.out.println(manager.allTask());
				taskNum = 1;
				break;
			default:
				System.out.println("����� ������������ ����� �������");
				taskNum =1;
				break;
			}
		}
	}
}
