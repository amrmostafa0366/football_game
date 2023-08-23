package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER = "postgresql";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5432;
    private static final String DB_NAME = "football_game";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";
    private static Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(String.format("jdbc:%s://%s:%d/%s", DRIVER, HOST, PORT, DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Can't open the connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DBConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close the connection: " + e.getMessage());
        }
    }
}
