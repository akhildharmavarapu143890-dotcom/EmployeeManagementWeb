package com.employee.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.employee")
public class HibernateConfig {

    // Database Connection
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds =
                new DriverManagerDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3307/akhil");
        ds.setUsername("root");
        ds.setPassword("");

        return ds;
    }

    // Hibernate Session Factory
    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean factory =
                new LocalSessionFactoryBean();

        factory.setDataSource(dataSource());

        factory.setPackagesToScan("com.employee.model");

        Properties properties = new Properties();

        properties.put(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQL8Dialect"
        );

        properties.put("hibernate.show_sql", "true");

        properties.put("hibernate.format_sql", "true");

        properties.put("hibernate.hbm2ddl.auto", "validate");

        factory.setHibernateProperties(properties);

        return factory;
    }

    // Hibernate Transaction Manager
    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager manager =
                new HibernateTransactionManager();

        manager.setSessionFactory(
                sessionFactory().getObject()
        );

        return manager;
    }
}

