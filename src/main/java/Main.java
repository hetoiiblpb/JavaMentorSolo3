import exception.DBException;
import model.User;
import service.UserService;
import util.DBHelper;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws DBException, SQLException {   //Метод только для теста
        UserService userService = UserService.getInstance();
        DBHelper.getInstance();
        DBHelper.getProperties();
        if (args.length != 0) {
            DBHelper.getProperties().setProperty("driver", args[0]);
        }
        userService.addUser(new User("DartAxis", "c2h5oh", "mail", 32L));
        userService.addUser(new User("Kostya", "123", "mail@ghb.ru", 29L));
        userService.addUser(new User("РусскоеИмя", "123", "maiaxl1", 15L));
        userService.addUser(new User("Другое", "357", "another@smail.ru", 16L));
        userService.addUser(new User("SovsemДругое", "789", "tooanother@vmail.ru", 20L));

        System.out.printf("%-3s%-13s%-10s%-20s%-8s%-6s%n", "ID", "User name", "Password", "E-mail", "Возраст", "Роль");
        System.out.println("_____________________________________________________________");
        for (User user : userService.getAllUsers()) {
            System.out.printf("%-3s%-13s%-10s%-20s%-8s%-6s%n", user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getAge(), user.getRole());
        }
        System.out.println("Удаляем " + userService.getUserById(4L).toString());
        userService.deleteUser(4L);
        userService.updateUser(new User(3L, "hetoiiblpb", "456", "hetoiiblpb@mail.ru", 20L));

        System.out.println("\n" + "email \"mail\" отсутствует в базе - " + (userService.checkUserByEmail("mail") ? "да" : "нет"));
        System.out.println("email \"xxxxx\" отсутствует в базе - " + (userService.checkUserByEmail("xxxxx") ? "да" : "нет") + "\n");

        System.out.println("Пара DartAxis/c2h5oh верна - " + (userService.verifyUserPassword("DartAxis", "c2h5oh") ? "да" : "нет"));
        System.out.println("Пара DartAxis/123 верна - " + (userService.verifyUserPassword("DartAxis", "ggg") ? "да" : "нет"));
        System.out.println("Пара gUnit/123 верна - " + (userService.verifyUserPassword("gUnit", "123") ? "да" : "нет"));
        System.out.println();

        System.out.printf("%-3s%-13s%-10s%-20s%-8s%-6s%n", "ID", "User name", "Password", "E-mail", "Возраст", "Роль");
        System.out.println("_____________________________________________________________");
        for (User user : userService.getAllUsers()) {
            System.out.printf("%-3s%-13s%-10s%-20s%-8s%-6s%n", user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.getAge(), user.getRole());
        }


    }
}

