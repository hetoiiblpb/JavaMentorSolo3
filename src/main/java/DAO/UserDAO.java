package DAO;

import Model.User;
import exception.DBException;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    boolean checkUserByEmail(String email) throws SQLException;

    boolean addUser(User user) throws SQLException;

    boolean deleteUser(Long id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    User getUserById(Long id) throws SQLException, DBException;

}
