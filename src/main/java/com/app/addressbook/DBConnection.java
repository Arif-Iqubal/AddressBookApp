package com.app.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/addressbook_db";
	private static final String USER = "root";
	private static final String PASSWORD = "Arif@123"; // change if needed

	public static Connection getConnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver not found!");
		}

		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}