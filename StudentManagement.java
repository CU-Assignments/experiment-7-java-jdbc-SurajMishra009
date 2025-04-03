package com.main;

import com.dao.StudentDAO;
import com.model.Student;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Department: ");
                    String dept = scanner.next();
                    System.out.print("Enter Marks: ");
                    float marks = scanner.nextFloat();
                    dao.addStudent(new Student(0, name, dept, marks));
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter New Department: ");
                    String newDept = scanner.next();
                    System.out.print("Enter New Marks: ");
                    float newMarks = scanner.nextFloat();
                    dao.updateStudent(id, newName, newDept, newMarks);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int delID = scanner.nextInt();
                    dao.deleteStudent(delID);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}
