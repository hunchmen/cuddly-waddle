package com.via.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.via.usermanagement.bean.User;
import com.via.usermanagement.utils.SqlUtils;

public class UserDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/user_schema?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "admin123";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	public UserDao() {
	}
	
	private static final String SELECT_USER_BY_ID =  "SELECT id, name, email, country FROM users WHERE ID =?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users";
	private static final String INSERT_USERS = "INSERT INTO users (name. email, country) VALUES (?, ?, ?)";
	private static final String DELETE_USERS = "DELETE FROM users WHERE id=?";
	private static final String UPDATE_USERS = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";
	
	protected Connection getConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("jdbcDriver");
			connection =  DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	//insert user
	public void insertUser(User user) {
		System.out.println(INSERT_USERS);
		try (Connection connection = getConnection(); 
				PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_USERS)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e);
		}
	}

	//select user by id
	public void selectUser(int id) {
		User user = null;
		//Establishing connection
		try (Connection connection = getConnection();
				//Create a statement using connection object
				PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_USER_BY_ID)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			//execute the query o udpate query
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e);
		}
	}
	
	
	private void printSQLException(SQLException ex) {
		for(Throwable e : ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
		
	}
	
}
