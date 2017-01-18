package com.progressoft.security.config;

import com.progressoft.security.jpa.factory.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.progressoft.security.jpa.factory.DataSourceFactory.*;

@Configuration
@ComponentScan("com.progressoft.security")
@EnableJpaRepositories("com.progressoft.security.jpa")
public class ContextConfig {

    private static final Map<String, Database> databases = new HashMap<>();
    private static final Map<String, String> dialects = new HashMap<>();


    static {
        databases.put("H2", Database.H2);
        databases.put("MySql", Database.MYSQL);
    }

    static {
        dialects.put("H2", "org.hibernate.dialect.H2Dialect");
        dialects.put("MySql", "org.hibernate.dialect.MySQLDialect");
    }

    @Inject
    Environment env;

    @Bean
    public DataSource dataSource() {
        return DataSourceFactory.make(env);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(databases.get(env.getProperty(DATABASE_TYPE)));
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);
        jpaVendorAdapter.setDatabasePlatform(dialects.get(env.getProperty(DATABASE_TYPE)));
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource);
        managerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        managerFactoryBean.setPackagesToScan("com.progressoft.security.jpa");
        return managerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
