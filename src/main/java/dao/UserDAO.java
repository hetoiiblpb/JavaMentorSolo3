package dao;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    <T> List<T> getAllUsers() throws SQLException;

    boolean checkUserByEmail(String email) throws SQLException;

    boolean verifyUserPassword(String name, String password) throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException, DBException;

}
