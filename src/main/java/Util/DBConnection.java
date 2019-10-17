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
    private static DBConnection instance;
    private static SessionFactory sessionFactory;
    private static Connection connection;

    private DBConnection() {
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(ConfigReader.getInstance().getDriverClass()).newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append(ConfigReader.getInstance().getUrl())                             //url and DB name
                    .append("?serverTimezone=").append(ConfigReader.getInstance().getServerTimeZone())     //serverTimeZone
                    .append("&characterEncoding=").append(ConfigReader.getInstance().getCharacterEncoding())  //CharSet
                    .append("&user=").append(ConfigReader.getInstance().getUserName())           //UserName
                    .append("&password=").append(ConfigReader.getInstance().getPassword());          //Password
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
        configuration.setProperty("hibernate.dialect", ConfigReader.getInstance().getHibernateDialect());
        configuration.setProperty("hibernate.connection.driver_class", ConfigReader.getInstance().getDriverClass());
        configuration.setProperty("hibernate.connection.characterEncoding", ConfigReader.getInstance().getCharacterEncoding());
        configuration.setProperty("hibernate.connection.url", ConfigReader.getInstance().getUrl());
        configuration.setProperty("hibernate.connection.username", ConfigReader.getInstance().getUserName());
        configuration.setProperty("hibernate.connection.password", ConfigReader.getInstance().getPassword());
        configuration.setProperty("hibernate.show_sql", ConfigReader.getInstance().getHibernateShowSql());
        configuration.setProperty("hibernate.hbm2ddl.auto", ConfigReader.getInstance().getHibernateHbm2ddlAuto());
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

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
}