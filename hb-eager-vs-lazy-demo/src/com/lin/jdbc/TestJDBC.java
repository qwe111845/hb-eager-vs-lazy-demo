package com.lin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hibernate";
		String password = "hibernate";

		try {
			System.out.println("Connect to database: " + jdbcUrl);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Connection successful!");
			
		} catch (SQLException error) {
			
			System.out.println(error.toString());
		}
	}

}
