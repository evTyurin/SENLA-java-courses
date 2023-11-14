package com.senlainc.warsaw.tyurin.util.dbconnection;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@DependencyClass
public class DbConnector {

    private final static Logger logger = Logger.getLogger(DbConnector.class);

    @ConfigProperty(propertyKey = Constants.DB_DRIVER)
    private String driver;
    @ConfigProperty(propertyKey = Constants.DB_URL)
    private String url;
    @ConfigProperty(propertyKey = Constants.DB_USER)
    private String user;
    @ConfigProperty(propertyKey = Constants.DB_PASSWORD)
    private String password;
    private static DbConnector INSTANCE;

    public Connection createConnection() throws Exception {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException exception) {
            logger.error("Failed to connect to database!", exception);
        }
        return connection;
    }
}
