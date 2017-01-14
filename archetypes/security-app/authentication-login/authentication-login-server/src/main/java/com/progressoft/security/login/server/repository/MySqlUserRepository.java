package com.progressoft.security.login.server.repository;

import com.progressoft.security.login.server.model.User;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserRepository implements UserRepository {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(MySqlUserRepository.class);
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/SECURITY_APP";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public MySqlUserRepository() {
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Could not open connection", e);
        }
    }

    @Override
    public User findUser(String name, String tenant) {
        try {
            return makeUser(executeQuery(name, tenant));
        } catch (SQLException e) {
            LOGGER.error("Could not execute query", e);
            return null;
        }
    }

    private ResultSet executeQuery(String name, String tenant) throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM USER WHERE USERNAME like '" + name + "' AND TENANT like '" + tenant + "'");
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        if (!resultSet.next())
            return null;
        String username = resultSet.getString("USERNAME");
        String secret = resultSet.getString("SECRET");
        String resultTenant = resultSet.getString("TENANT");
        return new User(username, secret, resultTenant);
    }
}

