package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1675";

    private static final UserService USER_SERVICE= new UserServiceImpl();

    public Connection getCOnnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            System.out.println("Connection was successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }

        return connection;
    }


    public static void main(String[] args) {
   USER_SERVICE.cleanUsersTable();
   USER_SERVICE.saveUser("Den","Landlord Fon Table", (byte) 30);
   USER_SERVICE.saveUser("Ivan","Maslakov", (byte) 40);
   USER_SERVICE.saveUser("Tarxyn","Bards Fon BotomInternet", (byte) 32);
   USER_SERVICE.saveUser("Kyril","Sigary", (byte) 27);
   USER_SERVICE.getAllUsers();
   USER_SERVICE.cleanUsersTable();
   USER_SERVICE.dropUsersTable();



    }
}
