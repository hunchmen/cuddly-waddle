package com.via.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.via.usermanagement.bean.User;

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
	public User selectUser(int id) {
		User user = null;
		//Step 1: Establishing connection
		try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_USER_BY_ID)){
			preparedStatement.setInt(1, id);
			//Step 3: execute the query o update query
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			//Step 4: Process the Resultset object.
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e); 
		}
		
		return user;
	}
	
	//select all users
	public List<User> selectAllUsers(){
		//using try-with-resources to avoid closing resources(boiler plate code)
		List<User> users = new ArrayList<>();
		
		//Step 1: establish connection
		try (Connection connection = getConnection();
				//Step 2:
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){
				System.out.println(preparedStatement);
				//Step 3: Execute query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				//Step 4: Process the result set object
				while(rs.next()) {
					int id = rs.getInt(1, id);
					String name = rs.getString(name);
					String email = rs.getString(email);
					String country = rs.getString(country);
					users.add(new User(id, name, email, country));
				}
		} catch (SQLException e) {
			// TODO: handle exception
			printSQLException(e);
		}
		
		return users;
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
