package service;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

import static dao.UserDAOFactory.getUserDAO;

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
        if (user.getEmail().isEmpty() || user.getName().isEmpty()) {
            return false;
        }
        try {
            if (getUserDAO().checkUserByEmail(user.getEmail())) {
                return getUserDAO().addUser(user);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return false;
    }

    public boolean deleteUser(Long id) throws DBException {
        try {
            return getUserDAO().deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean updateUser(User user) throws DBException {
        if (user.getEmail().isEmpty() || user.getName().isEmpty()) {
            return false;
        }
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


    public boolean checkUserByEmail(String mail) throws DBException {
        try {
            return getUserDAO().checkUserByEmail(mail);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean verifyUserPassword(String name, String password) throws DBException {
        try {
            return getUserDAO().verifyUserPassword(name, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

}
