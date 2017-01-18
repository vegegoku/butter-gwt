package com.progressoft.security.jpa.factory;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceFactory {

    private static final Map<String, EnvDataSourceFactory> dataSourceBuilders=new HashMap<>();
    static {
        dataSourceBuilders.put("H2", e -> new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build());
        dataSourceBuilders.put("MySql", e -> new MySqlEnvDataSourceFactory().make(e));
    }

    public static final String DATABASE_TYPE = "security_app_ds_type";

    private DataSourceFactory(){}


    public static DataSource make(Environment environment){
        return dataSourceBuilders.get(environment.getProperty(DATABASE_TYPE)).make(environment);
    }
}
