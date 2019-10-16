package Util;

import Model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";
    private static SessionFactory sessionFactory;
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connection = getMysqlConnection();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://")             //db type
                    .append("127.0.0.1:")               //host name
                    .append("3306/")                    //port
                    .append("users?serverTimezone=UTC&")//db name
                    .append("characterEncoding=utf8&") //charset
                    .append("user=root&")               //login
                    .append("password=Qwerty3366");     //password
            System.out.println("URL: " + url.toString() + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

        @SuppressWarnings("UnusedDeclaration")
        private static Configuration getMySqlConfiguration() {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.characterEncoding","utf8");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "Qwerty3366");
            configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
            configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
            return configuration;
        }

        private static SessionFactory createSessionFactory() {
            Configuration configuration = getMySqlConfiguration();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        }


}