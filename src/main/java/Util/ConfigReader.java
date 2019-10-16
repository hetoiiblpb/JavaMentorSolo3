package Util;


import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private String driver;
    private String hibernateDialect;
    private String driverClass;
    private String characterEncoding;
    private String url;
    private String userName;
    private String password;
    private String hibernateShowSql;
    private String hibernateHbm2ddlAuto;
    private String serverTimeZone;

    private ConfigReader() {
        readConfig();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getDriver() {
        return driver;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getHibernateShowSql() {
        return hibernateShowSql;
    }

    public String getHibernateHbm2ddlAuto() {
        return hibernateHbm2ddlAuto;
    }

    public String getServerTimeZone() {
        return serverTimeZone;
    }

    private void readConfig() {
        Properties property = new Properties();
        try {
            property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (
                IOException e) {
            System.out.println("ОШИБКА: Файл config.properties не найден!");
            e.printStackTrace();
        }
        driver = property.getProperty("driver");
        hibernateDialect = property.getProperty("hibernateDialect");
        driverClass = property.getProperty("driverClass");
        characterEncoding = property.getProperty("characterEncoding");
        url = property.getProperty("url");
        userName = property.getProperty("userName");
        password = property.getProperty("password");
        hibernateShowSql = property.getProperty("hibernateShowSql");
        hibernateHbm2ddlAuto = property.getProperty("hibernateHbm2ddlAuto");
        serverTimeZone = property.getProperty("serverTimezone");
    }

}
