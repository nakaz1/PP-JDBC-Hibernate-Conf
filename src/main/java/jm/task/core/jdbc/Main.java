package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("a", "b", (byte) 22);
        userService.saveUser("b", "c", (byte) 21);
        userService.saveUser("d", "e", (byte) 24);
        userService.saveUser("f", "g", (byte) 26);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }

}
