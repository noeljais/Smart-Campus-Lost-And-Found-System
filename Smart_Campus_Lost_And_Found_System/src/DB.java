import java.sql.*;

public class DB {
    private static Connection conn;

    public static Connection get() throws Exception {
        // Use PostgreSQL driver for Supabase
        Class.forName("org.postgresql.Driver");
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:6543/postgres",
                    "postgres.sfpvvvwtvbrlyibquvhx",
                    "N1o2e3l4@mcka");
        }
        return conn;
    }
}
