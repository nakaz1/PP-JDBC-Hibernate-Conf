package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static String URL = "jdbc:mysql://localhost:3307/mydb";
    private static String LOGIN = "root";
    private static String PASSWORD = "Qwerty123";

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static SessionFactory sessionFactory = null;

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

    public static SessionFactory getSessionHibernate() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class",DRIVER)
                    .setProperty("hibernate.connection.url",URL)
                    .setProperty("hibernate.connection.username",LOGIN)
                    .setProperty("hibernate.connection.password",PASSWORD)
                    .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                    .setProperty("current_session_context_class","thread")
                    .addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }


}

