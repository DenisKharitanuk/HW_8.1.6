package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {


    private static final UserService USER_SERVICE= new UserServiceImpl();

    public static void main(String[] args) {
   USER_SERVICE.createUsersTable();
   USER_SERVICE.saveUser("Den","Landlord Fon Table", (byte) 30);
   USER_SERVICE.saveUser("Ivan","Maslakov", (byte) 40);
   USER_SERVICE.saveUser("Tarxyn","BardsFonBotom", (byte) 32);
   USER_SERVICE.saveUser("Kyril","Sigary", (byte) 27);
   USER_SERVICE.getAllUsers();
   USER_SERVICE.cleanUsersTable();
   USER_SERVICE.dropUsersTable();



    }
}
