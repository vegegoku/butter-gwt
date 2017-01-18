package com.progressoft.security.jpa.factory;

import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@FunctionalInterface
public interface EnvDataSourceFactory {
    DataSource make(Environment environment);
}
