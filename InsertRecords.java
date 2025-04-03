package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertRecords {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://127.0.0.1:3306/my_database";
        String user = "root";
        String password = "@Webdev123"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Loaded Successfully");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            String query = "INSERT INTO Employee (EmpID, Name, Salary) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, 104); 
            pstmt.setString(2, "Michael Scott"); 
            pstmt.setFloat(3, 75000.0f); 

       
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Record inserted successfully!");
            }

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
