package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnectionJDBC();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id int(100) NOT NULL AUTO_INCREMENT PRIMARY KEY," + " username VARCHAR(100)," + " lastname VARCHAR(100), " + " age int)");
            System.out.println("Table create");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement prs = connection.prepareStatement("INSERT INTO users(username, lastname, age) VALUES (?, ?, ?)")){
            prs.setString(1, name);
            prs.setString(2, lastName);
            prs.setByte(3, age);
            prs.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement  prs = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            prs.setLong(1,id);
            prs.executeUpdate();
            System.out.println("User с ID " + id + " был удален");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(PreparedStatement prs = connection.prepareStatement("SELECT * FROM users")){
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;

    }

    public void cleanUsersTable() {
        try(PreparedStatement prs = connection.prepareStatement("TRUNCATE TABLE users")) {
            prs.executeUpdate();
            System.out.println("Table clear");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
