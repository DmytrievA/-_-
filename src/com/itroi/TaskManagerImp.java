package com.itroi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebService;

@WebService(endpointInterface="com.itroi.TaskManager")
public class TaskManagerImp implements TaskManager{

	@Override
	public String addTask(String userName, String title, String description, String startDate, String finishDate) throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement("INSERT INTO `task` (`username`, `title`, `description`, `startDate`, `finishDate`)"+
								"VALUES (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			prstmt.setString(1, userName);
			prstmt.setString(2, userName);
			prstmt.setString(3, userName);
			prstmt.setString(4, startDate);
			prstmt.setString(5, finishDate);
			
			result.append("Was updated " +prstmt.executeUpdate()).append(" rows\n");
			 
			try(ResultSet rs = prstmt.getGeneratedKeys()){
				if(rs.next()){
					result.append("New task id: " + rs.getLong(1));
				}
				else {
					throw new SQLException("Creating task failed, no ID returned.");
				}
			}
		} catch (SQLException e) {
			result = new StringBuilder("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	@Override
	public String deleteTaskById(int idTask) throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		int num = 0;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement("DELETE FROM task WHERE taskid=?");
			prstmt.setInt(1, idTask);
			num = prstmt.executeUpdate();
			if(num == 0){
				throw new SQLException("Deletind task failed, no rows deleted.");
			} else {
				result.append("Was deleted " + num + "rows").append(System.getProperty("line.separator"));
			}
		} catch (SQLException e) {
			result = new StringBuilder("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	@Override
	public String deleteTaskByUsernameAndTitle(String userName, String title) throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		int num = 0;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement("DELETE FROM task WHERE title=? AND username=?");
			prstmt.setString(1,title);
			prstmt.setString(2,userName);
			num = prstmt.executeUpdate();
			if(num == 0){
				throw new SQLException("Deletind task failed, no rows deleted.");
			} else {
				result.append("Was deleted " + num + "rows").append(System.getProperty("line.separator"));
			}
		} catch (SQLException e) {
			result.append("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	@Override
	public String allTask() throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement("SELECT taskid,username,title FROM task");
			ResultSet rs = prstmt.executeQuery();
			Integer num =0;
			while(rs.next())
			{
				result.append("Task ID: ").append(rs.getString("taskid")).append(System.getProperty("line.separator"))
				.append("User: ").append(rs.getString("username"))
				.append("Task Title: ").append(rs.getString("title"))
				.append(System.getProperty("line.separator"));
				num++;
			}
			result.append("Task number: ").append(num.toString());
		} catch (SQLException e) {
			result.append("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	@Override
	public String dropTask() throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement("TRUNCATE task");
			int rs = prstmt.executeUpdate();
			result.append("Delete rows: ").append(rs).append(System.getProperty("line.separator"));
		}
		catch (SQLException e){
			result = new StringBuilder("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	private static final String UPDATE_TASK_BY_USER_ADN_TITLE = 
			"UPDATE `task` SET `description`=?, `startDate`=?, `finishDate`=? WHERE `title`=? AND `username`=?";
	@Override
	public String changeTaskByUsernameAndTitle(String userName, String title, String description, String startDate, String finishDate) throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		StringBuilder result = new StringBuilder();
		int num;
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement(UPDATE_TASK_BY_USER_ADN_TITLE);
			prstmt.setString(2, startDate);
			prstmt.setString(3, finishDate);
			prstmt.setString(1, description);
			prstmt.setString(4, title);
			prstmt.setString(5, userName);
			num = prstmt.executeUpdate();
			result.append("Was updated " + num + " rows\n");
			if(num == 0){
				throw new SQLException("Updating task failed, no update were performed.");
			} else {
				ResultSet res = prstmt.getResultSet();
				while(res.next()){
					result.append(res.getString("idtask")).append(System.getProperty("line.separator"))
					.append("User: ").append(res.getString("username"))
					.append("Task Title").append(res.getString("title"))
					.append(System.getProperty("line.separator"));
				}
			}
		}
		catch (SQLException e){
			result = new StringBuilder("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}

	private static final String UPDATE_TASK_BY_ID = "UPDATE `task` SET `description`=?, `startDate`=?, `finishDate`=? WHERE `taskid`=?";
	@Override
	public String changeTaskById(int idTask, String description, String startDate, String finishDate) throws ClassNotFoundException {
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/taskmanagerservice";
		Class.forName("com.mysql.jdbc.Driver");
		PreparedStatement prstmt = null;
		StringBuilder result = new StringBuilder();
		try(Connection con = DriverManager.getConnection(url, user, pass)){
			prstmt = con.prepareStatement(UPDATE_TASK_BY_ID,Statement.RETURN_GENERATED_KEYS);
			prstmt.setString(2, startDate);
			prstmt.setString(3, finishDate);
			prstmt.setString(1, description);
			prstmt.setInt(4, idTask);
			result.append("Was updated " + prstmt.executeUpdate() + " rows").append(System.getProperty("line.separator"));						
		}
		catch (SQLException e){
			result = new StringBuilder("Bed request resul: ").append(e.getMessage()).append(System.getProperty("line.separator"));
			e.printStackTrace();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
}
