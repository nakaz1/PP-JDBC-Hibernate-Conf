package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static String URL = "jdbc:mysql://localhost:3307/mydb";
    private static String LOGIN = "root";
    private static String PASSWORD = "Qwerty123";

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";



    public static Connection getConnectionJDBC() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection successful!");

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
