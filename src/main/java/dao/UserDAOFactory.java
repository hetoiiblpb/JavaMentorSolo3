package dao;

import util.DBHelper;

public class UserDAOFactory {
    public static UserDAO getUserDAO() {
        String driver = DBHelper.getProperties().getProperty("driver").toLowerCase();  //Читаем из конфига параметр Driver
        switch (driver) {
            case ("hibernate"): {
                return UserDAOHibernateImpl.getInstance(DBHelper.getInstance().getSessionFactory());
            }
            default: {
                return UserDAOJDBCImpl.getInstance(DBHelper.getInstance().getConnection());
            }
        }
    }
}
