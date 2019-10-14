import Model.User;
import Service.UserService;
import exception.DBException;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws DBException, SQLException {
        UserService userService = UserService.getInstance();
        userService.dropTable();
        userService.createTable();
        userService.addUser(new User("name","mail",10L));
        userService.addUser(new User("name1","mail1",20L));
        userService.addUser(new User("nawamdsve1","maiaxl1",30L));
        userService.addUser(new User("naamsdve1","ma123il1",40L));
        userService.addUser(new User("nasvdme1","maisxl1",50L));
        for (User user:userService.getAllUsers()) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail() + " " + user.getAge());
        }
        userService.deleteUser(3L);
        userService.updateUser(new User(2L,"name111","mail111",20L));
        for (User user:userService.getAllUsers()) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail() + " " + user.getAge());
        }


    }

}
