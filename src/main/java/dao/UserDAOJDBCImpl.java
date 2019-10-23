package dao;

import exception.DBException;
import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBCImpl implements UserDAO {
    private static UserDAOJDBCImpl instance;
    private static Connection connection;

    private UserDAOJDBCImpl(Connection connection) throws SQLException {
        this.connection = DBHelper.getConnection();
        PreparedStatement stmt = connection.prepareStatement("create table if not exists users (id bigint auto_increment, name varchar(32), password varchar(128), mail varchar(128), age bigint, role varchar(5) not null default 'user ', primary key (id))");
        stmt.execute();
        stmt.close();
        if (checkUserByEmail(admin.getEmail())) {
            addUser(admin);
        }
    }

    public static UserDAOJDBCImpl getInstance(Connection connection) throws SQLException {
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
                    result.getString("password"),
                    result.getString("mail"),
                    result.getLong("age"),
                    result.getString("role")));
        }
        statement.close();
        return users;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        String stat = "INSERT INTO users.users (name, password,  mail, age, role) values (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(stat);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setLong(4, user.getAge());
        statement.setString(5, user.getRole());
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
                resultSet.getString("password"),
                resultSet.getString("mail"),
                resultSet.getLong("age"),
                resultSet.getString("role"));
        statement.close();
        return user;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users set name = ?, password = ?, mail = ?, age = ? WHERE id = ? ");
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setLong(4, user.getAge());
        statement.setLong(5, user.getId());
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

    @Override
    public boolean verifyUserPassword(String name, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users.users WHERE name = ? AND  password= ?");
        statement.setString(1, name);
        statement.setString(2, password);
        boolean res = statement.executeQuery().next();
        statement.close();
        return res;
    }


    public void refreshTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(" drop table if exists users ");
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(32), password varchar(128), mail varchar(128), age bigint, role varchar(5) not null default 'user ', primary key (id))");
        stmt.close();
    }


}
