package com.example.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class EmployeeData {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://127.0.0.1:3306/my_database";
        String user = "root";
        String password = "@Webdev123"; 
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver Loaded Successfully");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Employee";

            ResultSet rs = stmt.executeQuery(query);

            System.out.println("EmpID | Name | Salary");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                float salary = rs.getFloat("Salary");
                System.out.println(id + " | " + name + " | " + salary);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
