package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnection1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            String url = "jdbc:mysql://localhost:3306/testdb";
            String username = "root";
            String password = "ekisha"; // Use the correct password
            conn = DriverManager.getConnection(url, username, password);

            // Create a statement object
            stmt = conn.createStatement();

            // Execute a query
            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Close the result set
            rs.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        } finally {
            // Close the statement and connection
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}