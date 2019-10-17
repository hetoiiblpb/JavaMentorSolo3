package DAO;

import Util.ConfigReader;

public class UserDAOFactory {

    public static UserDAO getUserDAO() {
        String driver = ConfigReader.getInstance().getDriver().toLowerCase() + " ";  //Читаем из конфига параметр Driver
        if (driver.contains("hibernate")) {
            return UserDAOImplHibernate.getInstance();
        } else {
            return UserDAOImplJDBC.getInstance();
        }
    }
}
