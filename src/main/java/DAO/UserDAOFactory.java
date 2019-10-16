package DAO;

public class UserDAOFactory {
    static String driver = "Hibernate";
    public static UserDAO getUserDAO(){
        if (driver.equals("Hibernate")) {
            return UserDAOImplHibernate.getInstance();
        }
        if (driver.equals("JDBC")){
            return UserDAOImplJDBC.getInstance();
        }
        return null;
    }
}
