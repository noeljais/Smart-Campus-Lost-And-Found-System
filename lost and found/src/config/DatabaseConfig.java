package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database configuration and connection management
 */
public class DatabaseConfig {

    // Using Supabase Connection Pooler (Tokyo Region) - Exact User Host
    private static final String DB_URL = "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:6543/postgres";
    private static final String DB_USER = "postgres.sfpvvvwtvbrlyibquvhx";
    private static final String DB_PASSWORD = "N1o2e3l4@mcka";

    // Connection pool settings
    private static final int MAX_CONNECTIONS = 10;
    private static Connection connection = null;

    /**
     * Get database connection
     * 
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Create connection if it doesn't exist or is closed
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection established successfully!");
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Failed to connect to database!");
            System.err.println("URL: " + DB_URL);
            System.err.println("User: " + DB_USER);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection!");
            e.printStackTrace();
        }
    }

    /**
     * Test database connection
     * 
     * @return true if connection successful
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
