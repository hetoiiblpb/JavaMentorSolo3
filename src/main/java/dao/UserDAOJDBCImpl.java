package dao;

import Model.User;
import Util.DBHelper;
import exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO {
    private static UserDAOJDBCImpl instance;
    private static Connection connection;

    private UserDAOJDBCImpl(Connection connection) {
        this.connection = DBHelper.getConnection();
    }

    public static UserDAOJDBCImpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new UserDAOJDBCImpl(connection);
        }
        return instance;
    }
    @Override
    public <T> List<T> getAllUsers() throws SQLException {
        List<T> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            users.add((T) new User(result.getLong("id"),
                    result.getString("name"),
                    result.getString("mail"),
                    result.getLong("age")));
        }
        statement.close();
        return users;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public boolean checkUserByEmail(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users.users WHERE mail = ?");
        statement.setString(1, email);
        boolean res = !statement.executeQuery().next();
        statement.close();
        return res;
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE users");
        stmt.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(32), mail varchar(128), age bigint, primary key (id))");
        stmt.close();
    }


}
