package Service;

import DAO.UserDAO;
import Model.User;
import Util.DBConnection;
import exception.DBException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserService {
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public List<User> getAllUsers() throws DBException {
        try {
            return getUserDAO().getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean addUser(User user) throws DBException {
        try {
//        if ( getUserDAO().checkUserByEmail(user.getEmail()))
            return getUserDAO().addUser(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean deleteUser(Long id) throws DBException {
        try {
            return getUserDAO().deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean updateUser(User user) throws DBException {
        try {
            return getUserDAO().updateUser(user);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserById(Long id) throws DBException {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void dropTable() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.executeUpdate("TRUNCATE TABLE users");
        stmt.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = DBConnection.getConnection().createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(32), mail varchar(128), age bigint, primary key (id))");
        stmt.close();
    }

    public boolean checkUserByEmail(String mail) throws DBException {
        try {
            return getUserDAO().checkUserByEmail(mail);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    private static UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }
}
