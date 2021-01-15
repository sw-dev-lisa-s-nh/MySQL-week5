/*
 * Promineo Tech BESD Bootcamp
 * MySQL Week 5 Coding Assignment
 * 
 * DBConnection Class
 * JavaSE-1.8
 */

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {

	private static Connection connection;
	private final static String URL = "jdbc:mysql://localhost:3306/orchestra";
	private final static String USER = "root";
	private static String password = "";
	private static Scanner scanner = new Scanner(System.in);
	private static DBConnection instance;
		
	/*
	 * Constructor
	 */
	
	private DBConnection (Connection connection) {
		this.connection = connection;
	}
	
	
	public static Connection getConnection() {
		if (instance == null) {
			try {				
				System.out.println("UserName: " + USER);
				System.out.print("Enter your password: ");
				password = scanner.nextLine();
				connection = DriverManager.getConnection(URL,USER,password);
				instance = new DBConnection(connection);
				System.out.println("\n\n\n\n");				
			} catch (SQLException e) {
				e.printStackTrace();				
			}
		}		
		return DBConnection.connection;
		
	}
}
