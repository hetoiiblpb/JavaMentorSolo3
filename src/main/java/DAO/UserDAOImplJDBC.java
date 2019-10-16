package DAO;

import Model.User;
import Util.DBConnection;
import exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplJDBC implements UserDAO {
    private static UserDAOImplJDBC instance;
    private static Connection connection;

    private UserDAOImplJDBC() {
        this.connection = DBConnection.getConnection();
    }

    public static UserDAOImplJDBC getInstance() {
        if (instance == null) {
            instance = new UserDAOImplJDBC();
        }
        return instance;
    }

    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            users.add(new User(result.getLong("id"),
                    result.getString("name"),
                    result.getString("mail"),
                    result.getLong("age")));
        }
        statement.close();
        return users;
    }

    public boolean addUser(User user) throws SQLException {
        String stat = "INSERT INTO users.users (name,  mail, age) values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(stat);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setLong(3, user.getAge());
        if (statement.executeUpdate() != 0) {
            statement.close();
            return true;
        }
        statement.close();
        return false;
    }

    public boolean deleteUser(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users.users where id = ?");
        statement.setLong(1, id);
        if (statement.executeUpdate() != 0) {
            statement.close();
            return true;
        }
        statement.close();
        return false;
    }

    public User getUserById(Long id) throws SQLException, DBException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users.users where id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        User user = new User(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("mail"),
                resultSet.getLong("age"));
        statement.close();
        return user;
    }

    public boolean updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users set name = ?, mail = ?, age = ? WHERE id = ? ");
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setLong(3, user.getAge());
        statement.setLong(4, user.getId());
        if (statement.executeUpdate() != 0) {
            statement.close();
            return true;
        }
        statement.close();
        return false;
    }

    public boolean checkUserByEmail(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users.users WHERE mail = ?");
        statement.setString(1, email);
        boolean res = !statement.executeQuery().next();
        statement.close();
        return res;
    }

    public static void dropTable() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE TABLE users");
        stmt.close();
    }

    public static void createTable() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(32), mail varchar(128), age bigint, primary key (id))");
        stmt.close();
    }



}
