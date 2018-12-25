package com.itroi;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TaskManager {
	
	@WebMethod
	public String addTask(String userName, String title, String description,
			String startDate, String finishDate) throws ClassNotFoundException;	
	
	@WebMethod
	public String deleteTaskById(int idTask) throws ClassNotFoundException;
	
	@WebMethod
	public String deleteTaskByUsernameAndTitle(String userName, String title) throws ClassNotFoundException;
	
	@WebMethod
	public String allTask() throws ClassNotFoundException;
	
	@WebMethod
	public String dropTask() throws ClassNotFoundException;
	
	@WebMethod
	public String changeTaskByUsernameAndTitle(String userName, String title,
			String description, String startDate, String finishDate) throws ClassNotFoundException;
	
	@WebMethod
	public String changeTaskById(int idTask, String description,
			String startDate, String finishDate) throws ClassNotFoundException;
}
