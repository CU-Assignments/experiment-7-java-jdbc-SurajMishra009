package com.example.jdbc;
import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://127.0.0.1:3306/my_database";
    static final String USER = "root";
    static final String PASSWORD = "@Webdev123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected to database!");

            while (true) {
                System.out.println("\n1. Insert Record");
                System.out.println("2. Read Records");
                System.out.println("3. Update Record");
                System.out.println("4. Delete Record");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertRecord(conn);
                        break;
                    case 2:
                        readRecords(conn);
                        break;
                    case 3:
                        updateRecord(conn);
                        break;
                    case 4:
                        deleteRecord(conn);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT RECORD
    static void insertRecord(Connection conn) throws SQLException {
        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID: ");
        int id = sc.nextInt();
        System.out.print("Enter ProductName: ");
        String name = sc.next();
        System.out.print("Enter Price: ");
        float price = sc.nextFloat();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setFloat(3, price);
        pstmt.setInt(4, qty);
        pstmt.executeUpdate();

        System.out.println("Record Inserted Successfully");
    }

    // READ RECORDS
    static void readRecords(Connection conn) throws SQLException {
        String query = "SELECT * FROM Product";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("ProductID | ProductName | Price | Quantity");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " | " +
                    rs.getString("ProductName") + " | " +
                    rs.getFloat("Price") + " | " +
                    rs.getInt("Quantity"));
        }
    }

    // UPDATE RECORD
    static void updateRecord(Connection conn) throws SQLException {
        String query = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Price: ");
        float price = sc.nextFloat();

        pstmt.setFloat(1, price);
        pstmt.setInt(2, id);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Record Updated Successfully");
        } else {
            System.out.println("No Record Found with the given ProductID");
        }
    }

    // DELETE RECORD
    static void deleteRecord(Connection conn) throws SQLException {
        String query = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();

        pstmt.setInt(1, id);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Record Deleted Successfully");
        } else {
            System.out.println("No Record Found with the given ProductID");
        }
    }
}
