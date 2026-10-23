package org.library_manager.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    private final String aUrl;
    private final String aUser;
    private final String aPassword;

    public MySQLConnection() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found — copy src/main/resources/config.properties.example to config.properties and fill in your credentials.");
            }
            props.load(in);
        } catch (java.io.IOException e) {
            throw new IllegalStateException("Cannot read config.properties", e);
        }
        this.aUrl = props.getProperty("db.url");
        this.aUser = props.getProperty("db.user");
        this.aPassword = props.getProperty("db.password");
    }

    public Connection manageConnection() throws SQLException {
        return DriverManager.getConnection(aUrl, aUser, aPassword);
    }
}
