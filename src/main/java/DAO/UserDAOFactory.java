package DAO;

import Util.ConfigReader;

public class UserDAOFactory {

    public static UserDAO getUserDAO(){
        String driver = ConfigReader.getInstance().getDriver();
        if (driver.equals("Hibernate")) {
            return UserDAOImplHibernate.getInstance();
        } else {
            return UserDAOImplJDBC.getInstance();
        }
    }
}
