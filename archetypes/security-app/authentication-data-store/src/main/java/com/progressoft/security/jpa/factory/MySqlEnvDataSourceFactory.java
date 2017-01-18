package com.progressoft.security.jpa.factory;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

public class MySqlEnvDataSourceFactory implements EnvDataSourceFactory {
    @Override
    public DataSource make(Environment e) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword(e.getProperty("security_app_ds_password"));
        dataSource.setUser(e.getProperty("security_app_ds_user"));
        dataSource.setURL(e.getProperty("security_app_ds_url"));
        dataSource.setDatabaseName(e.getProperty("security_app_ds_schema"));
        return dataSource;
    }
}
