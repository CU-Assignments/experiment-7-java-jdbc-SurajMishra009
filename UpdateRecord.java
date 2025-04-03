package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateRecord {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://127.0.0.1:3306/my_database";
        String user = "root";
        String password = "@Webdev123"; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Loaded Successfully");

     
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            String query = "UPDATE Employee SET Salary = ? WHERE EmpID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setFloat(1, 80000.0f); 
            pstmt.setInt(2, 104); 
            
            int rows = pstmt.executeUpdate(); // Returns number of rows updated
            if (rows > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No record found to update!");
            }

            
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
