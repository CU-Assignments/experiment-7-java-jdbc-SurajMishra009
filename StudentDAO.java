package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.List;
import com.model.Student;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root";  // Change to your MySQL username
    private static final String PASSWORD = "";  // Change to your MySQL password

    private Connection conn;

    // Constructor to establish database connection
    public StudentDAO() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 1️⃣ Insert a new student
    public void addStudent(Student student) {
        String query = "INSERT INTO Students (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setFloat(3, student.getMarks());
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ Retrieve all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("Department"), rs.getFloat("Marks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 3️⃣ Update student details
    public void updateStudent(int studentID, String name, String department, float marks) {
        String query = "UPDATE Students SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            stmt.setInt(4, studentID);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Delete a student
    public void deleteStudent(int studentID) {
        String query = "DELETE FROM Students WHERE StudentID=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentID);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
