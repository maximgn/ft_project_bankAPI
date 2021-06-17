package utils;
import java.sql.*;

public class DatabaseConnection {

    private static String DB_URL = "jdbc:h2:mem:Bank";
    private static String DB_USERNAME = "admin";
    private static String DB_PASSWORD = "";
    private static String DB_DRIVER = "org.h2.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createOrFillDatabase(String query) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.execute(query);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        finally{
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

}