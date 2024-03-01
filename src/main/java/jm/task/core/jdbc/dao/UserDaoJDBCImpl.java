package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserDaoJDBCImpl implements UserDao {


    @Override
    public void createUsersTable() {
        Connection connection = new Util().getCOnnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
            System.out.println("Таблица User создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Connection connection = new Util().getCOnnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = new Util().getCOnnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, lastName, age) values (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println(" User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Connection connection = new Util().getCOnnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Строка удалена ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = new Util().getCOnnection();

        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Users")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection = new Util().getCOnnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
